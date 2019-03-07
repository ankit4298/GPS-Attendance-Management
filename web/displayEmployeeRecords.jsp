<%-- 
    Document   : displayEmployeeRecords
    Created on : Mar 7, 2019, 4:59:02 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Records</title>
        <link rel="stylesheet" type="text/css" href="css/table.css">
    </head>
    <body>


        <h2 align="center">Employee Details</h2>
        <hr>
        <br/>

        <c:choose>

            <c:when test="${empInfo == null or empInfo.isEmpty()}">
                No Records were found.
            </c:when>

            <c:otherwise>


                <table class="blueTable">
                    <thead>
                        <tr>
                            <th>EID</th>
                            <th>First Name</th>
                            <th>Middle Name</th>
                            <th>Last Name</th>
                            <th>Gender</th>
                            <th>Email</th>
                            <th>Phone no.</th>
                            <th>Work Dept</th>
                            <th>Address</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="info" items="${empInfo}">
                            <tr>
                                <td>${info.eid}</td>
                                <td>${info.firstname}</td>
                                <td>${info.middlename}</td>
                                <td>${info.lastname}</td>
                                <td>${info.gender}</td>
                                <td>${info.email}</td>
                                <td>${info.phno}</td>
                                <td>${info.workdept}</td>
                                <td>${info.address}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>

        </c:choose>
    </body>
</html>
