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
        <title>Login Records</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${loginInfo == null or loginInfo.isEmpty()}">
                No Records were found.
            </c:when>
            <c:otherwise>
                <table border=1>

                    <tr><th>username</th><th>password</th><th>login status</th></tr>
                            <c:forEach var="info" items="${loginInfo}">
                        <tr><td>${info.uname}</td><td>${info.pass}</td><td>${info.loginstatus}</td></tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>