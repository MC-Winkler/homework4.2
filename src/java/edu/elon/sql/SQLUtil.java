/*
 * Copyright (c) 2015 Michael Winkler & Mitchell Thompson
 */

package edu.elon.sql;

import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class SQLUtil {

    public static String getHtmlTable(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        
        String name = "";
        String id = "";
        String email = "";
        String title = "";
        String duedateString = "";
        
        SimpleDateFormat formattedDate = new SimpleDateFormat("MM-dd-yyyy");
        Date duedate;
        
        Calendar cal = Calendar.getInstance();
        java.util.Date currentDate = cal.getTime();
        
        htmlTable.append("<h2> Currently checked out books </h2>");
        htmlTable.append("<table>");

        // add header row
        htmlTable.append("<tr>");
        
        htmlTable.append("<th>");
        htmlTable.append("Patron Name");
        htmlTable.append("</th>");
        
        htmlTable.append("<th>");
        htmlTable.append("Email Address");
        htmlTable.append("</th>");
        
        htmlTable.append("<th>");
        htmlTable.append("Book Title");
        htmlTable.append("</th>");
        
        htmlTable.append("<th>");
        htmlTable.append("Due Date");
        htmlTable.append("</th>");
        
        htmlTable.append("<th>");
        htmlTable.append("Overdue");
        htmlTable.append("</th>");
        
        htmlTable.append("</tr>");

        // add all other rows
        while (results.next()) {
        
          name = results.getString(2) + " " + results.getString(3);
          email = results.getString(4);
          title = results.getString(1);
          duedate = results.getDate(5);
          duedateString = results.getString(5);
          id = results.getString(6);

          htmlTable.append("<tr>");

          htmlTable.append("<td>");
          htmlTable.append(name);
          htmlTable.append("</td>");

          htmlTable.append("<td>");
          htmlTable.append(email);
          htmlTable.append("</td>");

          htmlTable.append("<td>");
          htmlTable.append(title);
          htmlTable.append("</td>");

          htmlTable.append("<td>");
          htmlTable.append(formattedDate.format(duedate));
          htmlTable.append("</td>");

          htmlTable.append("<td>");
          if (duedate.before(currentDate))
            htmlTable.append("overdue");
          htmlTable.append("</td>");

          htmlTable.append("<td>");
          htmlTable.append("<a class=\"button\" href=\"library?action=checkin&"
                  + "id=" + id + "\" /> Check in </a>" );
          htmlTable.append("</td>");

          htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");
        return htmlTable.toString();
    }
}