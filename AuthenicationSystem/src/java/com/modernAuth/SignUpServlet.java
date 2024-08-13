package com.modernAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

/**
 *
 * @author Aditya Pawar
 */

class OtpGenerator{
    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        
        System.out.println("Email" + email);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (email != null && password != null) {
            
            // Getting the otp here 
            String sotp = OtpGenerator.generateOTP();
            int otp = Integer.parseInt(sotp);
            
            
            // Here we are sending the otp to the email
            
            EmailSender emailSender = new EmailSender();
            
            String to = email;
            String subject = "OTP";
            String text = "Your otp for authentication is : " + otp + " for authentication";
            
            boolean result = emailSender.sendEmail(to, subject, text);
           
            if(result){
                out.println("Email is sent successfully");
            }
            else{
                out.println("Some error is occured while sending email : ( ");
            }
            
            // Instead of saving cookies we can save the session for this
            
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            
        } else {
            out.println("Error: Email or Password is missing.");
        }
        out.close();
    }
}
