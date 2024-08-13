package com.modernAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet{
    
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String forgetEmail = (String)httpSession.getAttribute("forgetEmail");
        String user_entered_pass = req.getParameter("pass");
        
        try{
            SessionFactory sessionFactory = new Configuration().configure("com/modernAuth/hibernate.cfg.xml").buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            // Here we are getting the salt of the field whom we have to change the field
            Query<String> q1 = session.createQuery("select u.salt from User u where u.email = :email" , String.class);
            q1.setParameter("email", forgetEmail);
            String salt = q1.uniqueResult();
            
            // Now we have to convert the userentered password + salt to hash
            String newHashedPassword = hashPassword(user_entered_pass, salt);
            
            Query q2 = session.createQuery("UPDATE User u SET u.password = :pass WHERE u.email = :email");
            q2.setParameter("pass", newHashedPassword);
            q2.setParameter("email", forgetEmail);
            
            int result = q2.executeUpdate();
            session.getTransaction().commit();
            session.close();
            
            if(result == 1){
                resp.setContentType("application/json");
                resp.getWriter().write("{\"status\":\"success\"}");
            }
       }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
