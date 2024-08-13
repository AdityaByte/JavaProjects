package com.modernAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/LoginInServlet")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        System.out.println("Login servlet is running successfully");

        String email = req.getParameter("email");
        String password = req.getParameter("pass");

        SessionFactory sessionFactory = new Configuration().configure("com/modernAuth/hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        User user = null;

        try {
            user = session.get(User.class, email);  
            if (user == null) {
                // Here if the user is not found, return an error message
                System.out.println("No email exists in the database");
                resp.getWriter().write("{\"status\":\"failure\" , \"message\":\"Invalid email or password.\"}");
                return;
            }
        } finally {
            session.close();  // Ensure the session is closed
            sessionFactory.close();
        }

        final String db_password = user.getPassword();
        final String db_salt = user.getSalt();

        String pass = password + db_salt;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b1 = md.digest(pass.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : b1) {
                sb.append(String.format("%02x", b));
            }

            String hashedPassword = sb.toString();

            boolean result = hashedPassword.equals(db_password);
            System.out.println(result);

            if (result) {
                resp.getWriter().write("{\"status\":\"success\"}");
            } else {
                resp.getWriter().write("{\"status\":\"failure\" , \"message\":\"Invalid email or password.\"}");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            resp.getWriter().write("{\"status\":\"failure\" , \"message\":\"An internal error occurred.\"}");
        }
    }
}
