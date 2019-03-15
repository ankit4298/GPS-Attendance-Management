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
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <link rel="stylesheet" type="text/css" href="css/index-theme.css">

        <title>Welcome</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $("a").click(function () {
                    $("#datahere").load($(this).attr('href'));
                    return false;
                });
            });
        </script>

    </head>
    <body>


        <div class="header">
            <h1 id="title-header" align="center">Attendance Management System</h1>
        </div>

        <div class="split left">
            <div class="centered">
                <div class="navoptions" id="parent">
                    <a href="employeeMap.jsp">Employee Map</a><br/>
                    <a href="LoginRecordsLookup">Display all login credentials</a><br/>
                    <a href="EmployeeRecordsLookup">Display all Employee Info</a><br/>
                    <a href="AttendanceLookup">Display all Attendance Info</a><br/>
                    <a href="registration.jsp">Register Employee</a><br/>
                    <a href="#">LOGIN</a>
                    <a href="#">LOGOUT</a>
                </div>
            </div>
        </div>

        <div class="split right">
            <div class="centered">
                <div class="pad-left" id="datahere">

                </div>
            </div>
        </div>


    </body>
</html>
