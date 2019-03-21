<%-- 
    Document   : index2
    Created on : Mar 15, 2019, 11:49:52 PM
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
        <title>OAMS</title>
    </head>

    <frameset rows = "10%,*" frameborder="0" noresize>
        <frame src = "header-window.html">
            <frameset cols = "16%,*">
                <frame src = "navigation.html">
                <frame src = "mainwindow.html" name = "contentwindow">
                        
                    </frame>
                        
                </frame>
            </frameset>
                
        </frame>
    </frameset>

</html>
