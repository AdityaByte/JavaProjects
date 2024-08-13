package com.modernAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/forgetPassword")
public class ForgetPasswordServlet extends HttpServlet{

    private int getOTP(){
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return otp;
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int otp = getOTP();
        
        String email = req.getParameter("email");
        
        // for giving response to the  jsp page
        resp.setContentType("application/json");
        
        if(email != null){
            SessionFactory sessionFactory = new Configuration().configure("com/modernAuth/hibernate.cfg.xml").buildSessionFactory();
            Session session = sessionFactory.openSession();
            
            String query = "SELECT 1 FROM User u WHERE u.email = :email";
            Query<?> q1 = session.createQuery(query);
            q1.setParameter("email", email);
            
            boolean exists = !q1.list().isEmpty();
            
            if(exists){
                
                String to = email;
                String subject = "Forget Password";
                String text = "<strong> Your OTP for forgetting your password is " + otp + "</strong>";

                try{
                    EmailSender emailSender = new EmailSender();
                    boolean result = emailSender.sendEmail(to, subject, text);

                    if(result){
                        HttpSession httpSession = req.getSession();
                        httpSession.setAttribute("forgetOTP", otp);
                        httpSession.setAttribute("forgetEmail", email);
                        resp.getWriter().write("{\"status\":\"success\"}");
                    }
                    else{
                        resp.getWriter().write("{\"status\":\"failure\" , \"message\":\"Invalid OTP, PLease try again.\"}");
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                resp.getWriter().write("{\"status\":\"failure\"}");
            }
        }
        else{
            System.out.println("Field is empty");
        }
    }
    
}


