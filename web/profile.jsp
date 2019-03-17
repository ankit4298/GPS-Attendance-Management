<%-- 
    Document   : profile.jsp
    Created on : Mar 17, 2019, 12:16:17 AM
    Author     : ANKIT
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="pure_java.GetProfile"%>
<%@page import="database.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("eid") == null) {
        out.print("** NOT AUTHORIZED **");
        return;
    }
%>

<%
    String eid = request.getParameter("eid");
    GetProfile getProfile=new GetProfile();
    ArrayList al=getProfile.getProfile(eid);
   
    String fullname=al.get(1)+" "+al.get(2)+" "+al.get(3);   // fn mn ln
    String gender=al.get(4).toString();
    String email=al.get(5).toString();
    String phno=al.get(6).toString();
    String workdept=al.get(7).toString();
    String address=al.get(8).toString();

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="text/css" href="images/website_icon.png">
        <link rel="stylesheet" type="text/css" href="css/theme.css">
        <title>User Profile</title>
    </head>
    <body>

        <table class="profile-table" width="60%" style="margin-left: 20%;margin-right: 20%" >
            <tr>
                <th colspan="4"><h3>Employee : <b><%=eid%> Profile</b></h3></th>
                <br/>
            </tr>
            <tr>
                <td>Name: <b><%=fullname%></b></td>
                <td rowspan="6"><div align="right"><img src="image.jsp?eid=<%=eid%>" width="260" height="320"/></div></td>
            </tr>
            <tr>
                <td>Gender: <b><%=gender%></b></td>
            </tr>
            <tr>
                <td>Email: <b><%=email%></b></td>
            </tr>
            <tr>
                <td>Contact No.: <b><%=phno%></b></td>
            </tr>
            <tr>
                <td>Work Department: <b><%=workdept%></b></td>
            </tr>
            <tr>
                <td>Address: <b><%=address%></b></td>
            </tr>
        </table>
    </body>
</html>
