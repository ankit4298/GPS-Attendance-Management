<%-- 
    Document   : registrationSuccess
    Created on : Mar 4, 2019, 4:10:20 PM
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
        <!--<meta http-equiv="Refresh" content="5;url=index.jsp">-->

        <title>Registration Success</title>
        <style>

            p{
                font-size: 30px;
            }

        </style>

    </head>
    <body>

        <%
            if (request.getAttribute("alreadyRegistered").toString().equals("0")) {
        %>

        <h2>Successfully Registered user <% out.print(request.getAttribute("eid"));%></h2>
        <!--<h4>Redirecting to Homepage, if not redirected automatically <a href="index.jsp">click here</a> . . .</h4>-->

        <%
            } else{
        %>
        
        <h2>User Already Registered</h2>
        <!--<h4>Redirecting to Homepage, if not redirected automatically <a href="index.jsp">click here</a> . . .</h4>-->
        
        <%
            }
        %>

    </body>
</html>
