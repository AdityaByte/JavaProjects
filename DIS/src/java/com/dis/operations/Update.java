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

public class Update extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String city = request.getParameter("city");
            String table = request.getParameter("table");
            Connection connection = CreateConnection.getConnection();
            String query = "update " + table + " set name=? , city=? where id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3 , id);
            
            int result = pstmt.executeUpdate();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<script>");
            out.println("alert('data is successfully updated in the database')");
            out.println("</script>"); 
            
            out.println("<center><h2> Data is successfully updated </h2></center>");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}