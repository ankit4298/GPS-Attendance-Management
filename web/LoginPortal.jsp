<%-- 
    Document   : LoginPortal
    Created on : Mar 16, 2019, 11:07:13 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int failedAttempt = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <link rel="stylesheet" type="text/css" href="css/registerForm.css">
        <link rel="stylesheet" type="text/css" href="css/index-theme.css">
        <link rel="stylesheet" type="text/css" href="css/theme.css">

        <title>Login Portal</title>
    </head>
    <body>

        <h1 class="header" align="center">Attendance Management System<br/>Login Portal</h1>
        <br/>

        <div style="margin-top: 80px">



            <div class="form-style-5">
                <form action="LoginWebsite" method="POST">

                    <fieldset>
                        <legend>Login</legend>
                        <br/>
                        <input type="text" name="eid" placeholder="Employee ID *" required>
                        <input type="password" name="password" placeholder="Password *" minlength="6" required>
                    </fieldset>
                    
                    <div class="failed-attempt">
                        <%
                            try {
                                failedAttempt = Integer.parseInt(request.getAttribute("failedAttempt").toString());
                            } catch (NullPointerException ne) {
                                failedAttempt = 0;
                            }
                            if (failedAttempt == 1) {
                                out.println("Incorrect ID or Password, Try Again . . .");
                            } else if(failedAttempt == 2){
                                out.println("Login avaliable with Administrator ID only !");
                            }
                        %>
                    </div><br/>
                    
                    <input type="submit" value="Login" />
                </form>
            </div>

        </div>

    </body>
</html>
