package com.dis.operations;

import com.dis.ConnectToDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectAll extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
        try{
            String table = request.getParameter("table");
            
            ConnectToDb obj = new ConnectToDb();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(obj.getDriverClassName() , "root" , "aditya");
            String query = "select * from "+ table;
            System.out.println(query);
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet set = pstmt.executeQuery();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            while(set.next()){
                out.println("<center>");
                out.println("<h2> Id : " + set.getInt(1) +"</h2>");
                out.println("<h2> Name : " + set.getString(2) +"</h2>");
                out.println("<h2> City : " + set.getString(3) +"</h2>");
                out.println("</center>");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
