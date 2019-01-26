<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pure_java.CreateJSON" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MAP</title>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBF_xtsW0UFyRCZJ64lz19mm0RbJL8805w&libraries=geometry"></script>
        <style>
            #map {
                width: 1000px;
                height: 600px;
            }
        </style>
    </head>
    <body>

        <%
            CreateJSON cj = new CreateJSON();
            String jsonText = cj.createJSONemployees();
        %>

        <input type="hidden" id="infoJSON" value=<%= jsonText%> />

        <div id="map">.</div>
        <div id="resultDIV"></div>
        <script type="text/javascript" src="js/puregoogleapi.js"></script>



    </body>
</html>
