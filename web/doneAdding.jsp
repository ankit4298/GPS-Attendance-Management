<%-- 
    Document   : doneAdding
    Created on : Jan 25, 2019, 1:34:18 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("eid") == null) {
        out.print("** NOT AUTHORIZED **");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <title>ADDED</title>
    </head>
    <body>
        <h1>Record Added</h1>
        
        
        ${name}
    </body>
</html>
