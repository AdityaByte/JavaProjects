package com.dis;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDb extends HttpServlet{
    
    private Connection connection;
    String driverClassName = "jdbc:mysql://localhost:3306/adityadatabase";
    
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
        try{
            // For getting the parameters
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            
           // Firstly we have to load the driver class of mysql
           Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loaded the driver successfully");
           connection = DriverManager.getConnection( driverClassName , username , password);
            System.out.println("Yeah connection established successfully...");
            
            RequestDispatcher rd = request.getRequestDispatcher("web.html");
            rd.forward(request, response);
            System.out.println("Request is forwarded successfully...");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public String getDriverClassName(){
        return driverClassName;
    }
}
