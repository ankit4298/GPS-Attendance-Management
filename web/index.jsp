<%-- 
    Document   : index
    Created on : Jan 26, 2019, 12:15:17 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <style>
            p{
                color: red;
                font-weight: bold;
                text-align: center;
                font-size: 32px;
            }
        </style>
    </head>
    <body>
        <br/>
        <p>Attendance Management System</p>
        <br/>
        <hr/>
        <br/>
        <br/>
        <a href="employeeMap.jsp">Employee Map</a><br/>
        <a href="LoginRecordsLookup">Display all login credentials</a><br/>
        <a href="GetImages?eid=101">eid 101 image disp</a><br/>
        <a href="checkInOut.jsp?eid=103&latitude=20.014316&longitude=73.764120">check in out</a>
        
        
    </body>
</html>
