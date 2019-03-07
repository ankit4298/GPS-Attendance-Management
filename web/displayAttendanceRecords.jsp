<%-- 
    Document   : displayAttendanceRecords
    Created on : Mar 7, 2019, 5:52:33 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Records</title>
        <link rel="stylesheet" type="text/css" href="css/table.css">

        <style>

            input[type=submit] {
                width: 6em;;
            }

        </style>

    </head>
    <body>

        <h2 align="center">Attendance Records</h2>
        <hr>
        <br/>
        
        <form align="right" action="AttendanceLookup">
            <label>Filter: </label>
            <input type="text" name="filterBox" required>
            <select id="filterchoice" name="filterChoice" required>
                <option value="1">Employee ID</option>
                <option value="2">Date</option>
            </select>   
            <!--<input type="date" data-date-inline-picker="true" name="date">-->
            <input type="submit" value="Filter">
        </form>

        <form align="right" action="AttendanceLookup">
            <input type="submit" value="Display All">
        </form>

        <br>
        <br>

        <c:choose>

            <c:when test="${attendRec == null or attendRec.isEmpty()}">
                No Records were found.
            </c:when>

            <c:otherwise>
                <table class="blueTable">
                    <thead>
                        <tr>
                            <th>EID</th>
                            <th>Date</th>
                            <th>In Time</th>
                            <th>Out Time</th>
                            <th>Duration</th>
                            <th>OutStatus</th>
                            <th>Last Update Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="info" items="${attendRec}">
                            <tr>
                                <td>${info.eid}</td>
                                <td>${info.date}</td>
                                <td>${info.intime}</td>
                                <td>${info.outtime}</td>
                                <td>${info.duration}</td>
                                <td>${info.outstatus}</td>
                                <td>${info.lastupdate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>

        </c:choose>

    </body>
</html>
