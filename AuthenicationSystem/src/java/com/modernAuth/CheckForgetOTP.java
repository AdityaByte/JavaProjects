/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modernAuth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkOTP")
public class CheckForgetOTP extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String user_entered_otp = req.getParameter("otp");
        
        HttpSession session = req.getSession();
        Integer forgetOTP = (Integer)session.getAttribute("forgetOTP");
        
        resp.setContentType("application/json");
        
        if(forgetOTP != null && user_entered_otp.equals(forgetOTP.toString())){
            resp.getWriter().write("{\"status\":\"success\"}");
        }
    }    
}
