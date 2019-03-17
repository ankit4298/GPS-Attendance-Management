<%-- 
    Document   : logout
    Created on : Mar 16, 2019, 11:26:40 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <title>logout page</title>
    </head>
    <body>

        <%
            request.getSession().removeAttribute("eid");
            out.print(request.getAttribute("eid"));
            
            response.sendRedirect("LoginPortal.jsp");
        %>

    </body>
</html>
