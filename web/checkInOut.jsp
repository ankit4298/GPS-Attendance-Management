<%-- 
    Document   : checkInOut
    Created on : Feb 8, 2019, 2:47:29 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check in out</title><script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBF_xtsW0UFyRCZJ64lz19mm0RbJL8805w&libraries=geometry"></script>
        
    </head>
    <body>
        
        <%
        
            String eid=request.getParameter("eid");
            String latitude=request.getParameter("latitude");
            String longitude=request.getParameter("longitude");
        
        %>
        
        <input type="hidden" id="eid" value=<%= eid %> />
        <input type="hidden" id="latitude" value=<%= latitude %> />
        <input type="hidden" id="longitude" value=<%= longitude %> />
        <div id="map"></div>
        <input type="hidden" id="result" name="result" value="" />
        
        <script type="text/javascript" src="js/checkInOut.js"></script>
        
    </body>
</html>
