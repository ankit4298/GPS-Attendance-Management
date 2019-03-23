<%-- 
    Document   : displayAttendanceRecords
    Created on : Mar 7, 2019, 5:52:33 PM
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
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <title>Add Remote Site</title>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBF_xtsW0UFyRCZJ64lz19mm0RbJL8805w&libraries=drawing"></script>
        <style>

            #map-canvas {
                width: 100%;
                height: 600px;
            }

        </style>

    </head>
    <body>

        <div class="subheader">
            <hr/>
            Add Remote Location
        </div>
        <br/>

        <div id="map-canvas"></div>

        <br/>
        <br/>
        
        <form action="AddRemoteSite" align="right">
            
            <label>Latitude: </label><input type="text" id="lat" name="latitude" placeholder="Latitude" readonly required /><br/>
            <label>Longitude: </label><input type="text" id="lng" name="longitude" placeholder="Longitude" readonly required /><br/>
            <label>Radius: </label><input type="text" id="rad" name="radius" placeholder="Radius" readonly required /><br/>
            <label>Site Name: </label><input type="text" name="site_name" placeholder="Site Name" required /><br/>
            <label>Location ID: </label><input type="text" name="locID" placeholder="Location ID" required /><br/>
            <input type="submit" value="SUBMIT"/>
            
        </form>

        <script type="text/javascript" src="js/createCircle.js"></script>
    </body>
</html>