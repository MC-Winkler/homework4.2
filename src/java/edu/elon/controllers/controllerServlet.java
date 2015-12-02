/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elon.controllers;

import edu.elon.business.CheckoutItem;
import edu.elon.data.CheckoutItemDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.elon.data.ConnectionPool;
import edu.elon.sql.SQLUtil;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mwinkler3
 */
public class controllerServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        Connection connection;
        String currentTableHTML = "";
            
        try {
          // load the driver
          Class.forName("com.mysql.jdbc.Driver");

          String dbURL = "jdbc:mysql://localhost:3306/mvc";
          String username = "root";
          String password = "sesame";

          //Determine if running on OpenShift by getting value of
          //OpenShift environement variable. If it is set (non null) then
          //we are and need to reset some variables. If no then no need to
          //reset and we are running locally.
          String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
          if ((host != null) && (host.trim().length() > 1)) {
            String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            String appname = System.getenv("OPENSHIFT_APP_NAME");
            username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            dbURL = "jdbc:mysql://" + host + ":" + port + "/" + appname;
          }
        } 
        
        catch (ClassNotFoundException e) {
          System.out.println(e);
        } 
      
        
        String url = "";
        String action = request.getParameter("action");
     
        if (action == null){
          url = "/index.jsp";
          action = "noAction";
        }
        
        if (action.equals("redirectToCheckout")){
          url = "/checkout.jsp";
        }

        else if (action.equals("manage")){
          currentTableHTML = CheckoutItemDB.selectAll();
          
          HttpSession session = request.getSession();
          session.setAttribute("currentTableHTML", currentTableHTML);
          url = "/manage.jsp";
        }

        else if (action.equals("checkout")){
          String title = request.getParameter("title");
          String firstname = request.getParameter("firstname");
          String lastname = request.getParameter("lastname");
          String email = request.getParameter("email");
          
          CheckoutItem currentItem = new CheckoutItem (title, firstname, lastname, email);
          CheckoutItemDB.insert(currentItem);
          request.setAttribute("currentItem", currentItem);
          url = "/thanks.jsp";
        }

        else if (action.equals("checkin")){

        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

  

}
