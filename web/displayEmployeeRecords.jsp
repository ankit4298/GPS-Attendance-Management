<%-- 
    Document   : displayEmployeeRecords
    Created on : Mar 7, 2019, 4:59:02 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <title>Employee Records</title>
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/theme.css">

        <script type="text/javascript" src="js/filter-table.js"></script>

        <style>
            span{
                display: none;
            }
        </style>

    </head>
    <body>

        <div class="subheader">
            <hr/>
            Employee Records
        </div>
        <br/>

        <form align="right">
            <label>Search: </label>

            <input type="text" id="filterByEID" onkeyup="filterTable()" onClick="this.setSelectionRange(0, this.value.length)" placeholder="Seacrh Records" />
            <select id="filterchoiceLocal" name="filterChoiceLocal" required>
                <option value="1">Employee ID</option>
                <option value="2" selected="">First Name</option>
                <option value="3">Last Name</option>
                <option value="4">Work Department</option>
            </select>
        </form>


        <span id="xlsFilename">Employee_Details</span>
        <script type="text/javascript" src="js/reportDownload.js"></script>

        <br/>

        <c:choose>
            <c:when test="${empInfo == null or empInfo.isEmpty()}">
                No Records were found.
            </c:when>

            <c:otherwise>


                <table border="1" id="tblData" class="blueTable">
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
                                <!--<td>${info.eid}</td>-->
                                <td> <a href="profile.jsp?eid=${info.eid}">       ${info.eid}</a></td>
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

        <br/>
        <a id="downloadBtn" onclick="exportTableToExcel('tblData')"><u>Download Data</u></a>
    </body>
</html>
