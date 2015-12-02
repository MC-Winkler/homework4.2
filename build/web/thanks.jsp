<%@ include file="/includes/header.html" %>
<!--
Copyright (c) 2015 Michael Winkler & Mitchell Thompson
-->
<div class="redOutline">
    <p>
        Thank you for your patronage of the Belk Library. You've successfully
        checked out the book, ${currentItem.title}. Please note that this book 
        is due back on ${currentItem.date}. A friendly email reminder will  be 
        sent to you if this book becomes overdue.
    </p>
    <a href="library" >Return to front page</a>
</div>
<%@ include file="/includes/footer.html" %>

