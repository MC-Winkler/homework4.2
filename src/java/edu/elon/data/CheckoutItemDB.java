/*
 * Copyright (c) 2015 Michael Winkler & Mitchell Thompson
 */

package edu.elon.data;

import java.sql.*;

import edu.elon.business.CheckoutItem;
import edu.elon.sql.SQLUtil;

public class CheckoutItemDB {

    public static int insert(CheckoutItem item) {
    
        int i = 0;
        String query
                = "INSERT INTO checkedout "
                + "(title, firstname, lastname, email, duedate) "
                + "VALUES (?, ?, ?, ?, ?);";
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
          
          Connection connection = 
                  DriverManager.getConnection(dbURL, username, password);
          PreparedStatement ps = null;
          
          ps = connection.prepareStatement(query);
          ps.setString(1, item.getTitle());
          ps.setString(2, item.getFirstName());
          ps.setString(3, item.getLastName());
          ps.setString(4, item.getEmail());
          ps.setDate(5, item.getDate());
          i = ps.executeUpdate();

          ps.close();
          connection.close();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
    return i;
    }

    public static int delete(String id) {
      System.out.println("id = " + id);
        int i = 0;
        String query = "DELETE FROM checkedout WHERE id = " + id +";";
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
          
          Connection connection = 
                  DriverManager.getConnection(dbURL, username, password);
          PreparedStatement ps = null;
          
          ps = connection.prepareStatement(query);
          i = ps.executeUpdate();

          ps.close();
          connection.close();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
      return i;
    
    }


    
    public static String selectAll(){
      
      String query = "SELECT * FROM checkedout;";
      String currentTableHTML = "";
      ResultSet resultSet = null;
      
      System.out.println("within selectAll");
      
      try {
          System.out.println("within the try ");
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
          
          Connection connection = 
                  DriverManager.getConnection(dbURL, username, password);
          PreparedStatement ps;
          ps = connection.prepareStatement(query);
          resultSet = ps.executeQuery();
          
          currentTableHTML = SQLUtil.getHtmlTable(resultSet);
          
          ps.close();
          connection.close();
          
          
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
      return currentTableHTML;

    }
}