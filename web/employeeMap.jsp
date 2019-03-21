<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pure_java.CreateJSON" %>

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

        <title>MAP</title>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBF_xtsW0UFyRCZJ64lz19mm0RbJL8805w&libraries=geometry"></script>
        <style>
            #map {
                width: 100%;
                height: 600px;
            }
        </style>
    </head>
    <body>


        <div class="subheader">
            <hr/>
            Employee Live Map
        </div>
        <br/>

        <%
            CreateJSON cj = new CreateJSON();
            String jsonText = cj.createJSONemployees();
        %>

        <input type="hidden" id="infoJSON" value=<%= jsonText%> />

        <input type="button" onclick="initialize()" id="views" value="RoadMap / Satellite View"/>
        <select id="viewChoice" name="viewChoice" required style="float: right">
            <option value="1">RoadMap View</option>
            <option value="2">Satellite View</option>
        </select>
        <br/>

        <div id="map">.</div>
        <div id="resultDIV"></div>
        
        <!--    '?1' is for revision no. to update web browser with latest version of script    -->
        <script type="text/javascript" src="js/puregoogleapi.js?1"></script>



    </body>
</html>
