<%-- 
    Document   : displayLoginRecords
    Created on : Jan 27, 2019, 1:00:02 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/website_icon.png">
        
        <title>Login Records</title>
        <link rel="stylesheet" type="text/css" href="css/table.css">
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        
        <script type="text/javascript" src="js/filter-table.js"></script>
        
    </head>
    <body>

        <table style="width: 100%">
            <tr>
                <td><a title="homepage" id="title-back" href="index.jsp"></a></td>
                <td><h1 id="title-header">Login Credentials</h1></td>
                <td></td>
            </tr>
        </table>
        <hr/>
        <br/>
        
        <form align="right">
            <label>Search: </label>
            <input type="text" id="filterByEID" onkeyup="filterTable()" onClick="this.setSelectionRange(0, this.value.length)" placeholder="Seacrh Records"/>
            <select id="filterchoiceLocal" name="filterChoiceLocal" style="display: none">
                <option value="1">Employee ID</option>
            </select>
        </form>
        <br/>

        <c:choose>
            <c:when test="${loginInfo == null or loginInfo.isEmpty()}">
                No Records were found.
            </c:when>
            <c:otherwise>

                <table id="tblData" class="blueTable">
                    <thead>
                        <tr>
                            <th>username</th>
                            <th>password</th>
                            <th>login status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="info" items="${loginInfo}">
                            <tr>
                                <td>${info.uname}</td>
                                <td>${info.pass}</td>
                                <td>${info.loginstatus}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:otherwise>
        </c:choose>
    </body>
</html>
