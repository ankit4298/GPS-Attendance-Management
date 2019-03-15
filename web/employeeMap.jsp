<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pure_java.CreateJSON" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <link rel="stylesheet" type="text/css" href="css/theme.css">

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


        <table style="width: 100%">
            <tr>
                <td><a title="homepage" id="title-back" href="index.jsp"></a></td>
                <td><h1 id="title-header">Live Employee Map</h1></td>
                <td></td>
            </tr>
        </table>
        <hr/>
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
        <script type="text/javascript" src="js/puregoogleapi.js"></script>



    </body>
</html>
