/*
 * Copyright (c) 2015 Michael Winkler & Mitchell Thompson
 */

package edu.elon.business;

import java.util.Calendar;

public class CheckoutItem {
    
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private java.sql.Date date;
    
    public CheckoutItem() {
      title = "";
      email = "";
      firstName = "";
      lastName = "";
      
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DAY_OF_MONTH, 14);
      java.util.Date twoWeeks = cal.getTime();
      date = new java.sql.Date(twoWeeks.getTime());
      
    }

    public CheckoutItem(String title, String firstname, String lastname, String email) {
      this.title = title;
      firstName = firstname;
      lastName = lastname;
      this.email = email;

      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DAY_OF_MONTH, 14);
      java.util.Date twoWeeks = cal.getTime();
      date = new java.sql.Date(twoWeeks.getTime());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getTitle() {
      return title;
    }
    
    public void setTitle (String title){
      this.title = title;
    }
    
    public java.sql.Date getDate () {
      return date;
    }
    
    public void setDate (java.sql.Date date){
      this.date = date;
    }
}