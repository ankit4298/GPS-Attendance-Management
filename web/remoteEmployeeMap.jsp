<%-- 
    Document   : remoteEmployeeMap
    Created on : Mar 19, 2019, 10:48:51 PM
    Author     : ANKIT
--%>

<%@page import="pure_java.CreateJSON"%>
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
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <title>Remote Employee Map</title>
    </head>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBF_xtsW0UFyRCZJ64lz19mm0RbJL8805w&libraries=geometry"></script>
    <style>
        #map {
            width: 100%;
            height: 600px;
        }
        .dont-show{
            display: none;
        }
    </style>
    <body>

        <div class="subheader">
            <hr/>
            Remote Employee Live Map
        </div>
        <br/>
        
        <%
            CreateJSON cj = new CreateJSON();
            String jsonText = cj.createJSONremoteEmployees();
            String jsonCoords = cj.createJSONremoteCoords();
        %>
        
        <input type="hidden" id="infoJSON" value=<%= jsonText%> />
        <input type="hidden" id="remoteCoords" value=<%= jsonCoords%> />

        <br/>

        <div id="map">.</div>
        <div id="resultDIV"></div>

        <script type="text/javascript" src="js/remoteEmp.js"></script>
    </body>
</html>
