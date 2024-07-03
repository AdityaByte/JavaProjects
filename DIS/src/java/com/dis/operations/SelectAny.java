package com.dis.operations;

import com.dis.CreateConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectAny extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
        try{
            Connection connection = CreateConnection.getConnection();
            int id = Integer.parseInt(request.getParameter("id"));
            String table = request.getParameter("table");
            
            String query = "select * from " + table + " where id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
            ResultSet set = pstmt.executeQuery();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            while(set.next()){
                out.println("<center>");
                out.println("<h2> Id = " + set.getInt(1) + "</h2>");
                out.println("<h2> Name = " + set.getString(2) + "</h2>");
                out.println("<h2> City = " + set.getString(3) + "</h2>");
                out.println("</center>");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
