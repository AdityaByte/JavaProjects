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

public class Insert extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        try{
            Connection connection = CreateConnection.getConnection();
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String city = request.getParameter("city");
            
            String query = "insert into student(id , name , city) values(?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2 , name);
            pstmt.setString(3, city);
            int r = pstmt.executeUpdate();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<center> <h2> Data is inserted successfully to the database " + r + " </h2> </center>");
            
            String query2 = "select * from student where id = ?";
            PreparedStatement pstmt2 = connection.prepareStatement(query2);
            pstmt.setInt(1 , id);
            
            ResultSet set = pstmt.executeQuery();
            
            while(set.next()){
                out.println("<center>");
                out.println("<h2> Id : " + set.getInt(1) + "</h2>");
                out.println("<h2> Name : " + set.getString(2) + "</h2>");
                out.println("<h2> City : " + set.getString(2) + "</h2>");

                out.println("</center>");
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
