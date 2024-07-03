package com.dis;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection{
    
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adityadatabase" , "root" , "aditya");
            return connection;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
