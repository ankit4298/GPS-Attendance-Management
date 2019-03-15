<%-- 
    Document   : registration
    Created on : Mar 4, 2019, 3:24:47 PM
    Author     : ANKIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/website_icon.png">
        <title>Registration</title>
        
        <link rel="stylesheet" type="text/css" href="css/registerForm.css">
        <link rel="stylesheet" type="text/css" href="css/theme.css">
    </head>
    <body>
        
        <div class="subheader">
            <hr/>
            Regestration of Employees
        </div>
        <br/>

        <div class="form-style-5">
            <form action="RegisterPortal">
                <fieldset>
                    <legend><span class="number">1</span> Employee Info</legend>
                    <input type="text" name="firstname" placeholder="Your First Name *" required>
                    <input type="text" name="middlename" placeholder="Your Middle Name *" required>
                    <input type="text" name="lastname" placeholder="Your Last Name *" required>
                    <label for="job">Gender :</label>
                    <select id="job" name="gender" required>
                        <option value="choose">choose gender *</option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>   
                    <input type="email" name="email" placeholder="Your Email *" required>
                    <input type="text" name="phno" placeholder="Your Contact number *" required>
                    <input type="text" name="workdept" placeholder="Your Work department *" required>
                    <textarea name="address" placeholder="Address *" required></textarea>
                       
                </fieldset>
                <fieldset>
                    <legend><span class="number">2</span> Login Info</legend>
                    <input type="text" name="eid" placeholder="Employee ID *" required>
                    <input type="password" name="password" placeholder="Password *" minlength="6" required>
                </fieldset>
                
                <input type="submit" value="Register" />
            </form>
        </div>

    </body>
</html>
