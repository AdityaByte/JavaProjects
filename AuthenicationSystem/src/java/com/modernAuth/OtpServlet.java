package com.modernAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Aditya Pawar
 */


@WebServlet("/OtpServlet")
public class OtpServlet extends HttpServlet {
    

    private String hashPassword(String password , String salt) throws NoSuchAlgorithmException{
                
        String combinedPass = password + salt;
        
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        
        byte[] b1 = messageDigest.digest(combinedPass.getBytes());
        
        StringBuilder sb = new StringBuilder();
        for(byte b :  b1){
            sb.append(String.format("%02x", b));
        }
        
        String hashedPassword = sb.toString();
        
        return hashedPassword;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Yeah, I'm under the OTP servlet");

        String userEnteredOtp = request.getParameter("otp");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        Integer sessionOTP = (Integer)session.getAttribute("otp");
        String sessionEmail = (String)session.getAttribute("email");
        String sessionPassword = (String)session.getAttribute("password");
        
        
        if(sessionOTP != null && sessionOTP.toString().equals(userEnteredOtp)){
            // Here are saving the data to the database
            
            try{
                
                SecureRandom secureRandom = new SecureRandom();
                byte[] b1 = new byte[16];
                secureRandom.nextBytes(b1);
                String salt = Base64.getEncoder().encodeToString(b1);
                
                User u1 = new User(new Date() , sessionEmail , hashPassword(sessionPassword, salt) , salt);
                
                SessionFactory sessionFactory = new Configuration().configure("com/modernAuth/hibernate.cfg.xml").buildSessionFactory();
                
                // Here we have to create a session for saving the entity
                
                Session session1 = sessionFactory.openSession();
                session1.beginTransaction();
                session1.save(u1);
                session1.getTransaction().commit();
                
                
                session.setAttribute("isAuthenticated", true);
                // sending json response
                response.getWriter().write("{\"status\":\"success\"}");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            

        }
        else{
            session.setAttribute("isAuthenticated", false);
            response.getWriter().write("{\"status\":\"failure\" , \"message\":\"Invalid OTP, PLease try again.\"}");
        }
        
       
    }
}
