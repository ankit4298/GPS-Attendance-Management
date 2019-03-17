<%-- 
    Document   : displayAttendanceRecords
    Created on : Mar 7, 2019, 5:52:33 PM
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
        <title>Attendance Records</title>
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/theme.css">

        <script type="text/javascript" src="js/filter-table.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.7/angular.min.js"></script>
        <script>
            var app1 = angular.module("myapp", []);
            app1.controller("myctlr", function ($scope) {
//                $scope.filterQuery = "All records";
            });
        </script>
    </head>


    <%!     // ---- remove

        String urlFilterBox = "All Records";
        String xlsFilename = "all_rec";
    %>
    <%
        urlFilterBox = request.getParameter("filterBox");
        try {
            if (urlFilterBox.toString().equals("null")) {
                urlFilterBox = "All Records";
            }
        } catch (NullPointerException ne) {
            urlFilterBox = "All Records";
        }
        // ---- 
    %>

    <div class="subheader">
        <hr/>
        Attendance Records
    </div>
    <br/>

    <div ng-app="myapp" ng-controller="myctlr">

        <form align="right">
            <label>Search: </label>

            <input type="text" id="filterByEID" onkeyup="filterTable()" onClick="this.setSelectionRange(0, this.value.length)" placeholder="Seacrh Records" ng-model="filterQuery" />
            <select id="filterchoiceLocal" name="filterChoiceLocal" required>
                <option value="1">Employee ID</option>
                <option value="2">Date</option>
            </select>
        </form>

        <script type="text/javascript" src="js/reportDownload.js"></script>

        <form action="AttendanceLookup">
            <input type="submit" value="Display All Records"/>
        </form>

        <button onclick="toggleAdvanceFilters()">Advance Filters</button>

        <br/>
        <br/>

        <div id="advanceFilters">
            <form action="AttendanceLookup" align="right">
                <fieldset>
                    <legend>Advance Filters</legend>

                    <div>Load the table with specific records with EID or Date</div><br/>

                    <!--used for advance Filtering-->
                    <input type="text" name="filterBox" id="filterBox" required>
                    <select id="filterchoice" name="filterChoice" required>
                        <option value="1">Employee ID</option>
                        <option value="2">Date</option>
                    </select>
                    <br>
                    <input type="submit" value="Filter">
                    <!---->
                </fieldset>
            </form>
        </div>

        <br>

        <div id="content-table">
            <br/>
            <c:choose>

                <c:when test="${attendRec == null or attendRec.isEmpty()}">
                    No Records were found.
                </c:when>

                <c:otherwise>
                    <div id="table-title">Showing Records for : <b><span id="xlsFilename">{{ filterQuery}}</span></b></div>
                    <table border="1" id="tblData" class="blueTable">
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
        </div>
    </div>

    <br>

    <a id="downloadBtn" onclick="exportTableToExcel('tblData')"><u>Download Data</u></a>



</html>
