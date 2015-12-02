<%@ include file="/includes/header.html" %>
<!--
Copyright (c) 2015 Michael Winkler & Mitchell Thompson
-->
  <div class="redOutline">
    <form id="info" action="library" method="POST">
      <h2>Checkout a book</h2><br>
      <div id="labels">
        First Name: <br>
        Last Name: <br>    
        Email Address: <br>       
        Book Title: <br>
      </div>
      <div id = "inputs">
        <input name="action" type="hidden" value="checkout">
        <input name="firstname" type="text" required><br>          
        <input name="lastname" type="text" required><br>
        <input name="email" type="email" required><br>
        <input name="title" type="text" id ="title" required><br>			 
        <input type="submit" value="Checkout">
      </div>      
    </form> 
</div>
<%@ include file="/includes/footer.html" %>