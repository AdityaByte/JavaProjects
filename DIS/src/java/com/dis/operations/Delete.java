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

public class Delete extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Connection connection = CreateConnection.getConnection();
            String query = "delete from student where id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            int r = pstmt.executeUpdate();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<center> <h2> Data is successfully deleted from the database " + r + " </h2> </center>");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
