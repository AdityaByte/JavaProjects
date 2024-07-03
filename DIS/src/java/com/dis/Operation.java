package com.dis;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Operation extends HttpServlet{
    
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
        Object operation = request.getParameter("operation");
        if(operation.equals("insert")){
            RequestDispatcher rd = request.getRequestDispatcher("insert.html");
            rd.include(request, response);
        }
        else if(operation.equals("selectall")){
            RequestDispatcher rd = request.getRequestDispatcher("selectall.html");
            rd.include(request, response);
        }
        else if(operation.equals("selectonly")){
            RequestDispatcher rd = request.getRequestDispatcher("selectany.html");
            rd.include(request, response);
        }
        else if(operation.equals("update")){
            RequestDispatcher rd = request.getRequestDispatcher("update.html");
            rd.include(request, response);
        }
        else if(operation.equals("delete")){
            RequestDispatcher rd = request.getRequestDispatcher("delete.html");
            rd.include(request, response);            
        }
        else{
            System.out.println(operation);
            System.out.println("hehe me is here");
        }
    }
    
}