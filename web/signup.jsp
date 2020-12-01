<%-- 
    Document   : signup
    Created on : Sep 20, 2020, 4:56:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    <body style="background-color:yellowgreen"/>
    <style>
        h1{
            text-align: center;
        }
        form{
            text-align: center;
        }
        div{
            text-align: center;
        }
        #userBox{
            width: 250px;
        }
        #passBox, #fullBox{
            width: 280px;
        }
        #conBox{
            width: 230px;
        }
    </style>
</head>
<body>
    <h1>Create New Account</h1>
    <div>
        <font color="red">
        ${requestScope.ERROR}
        </font>
    </div>
    <form action="MainController" method="POST" >
        <font color="red">
        ${requestScope.INVALID.usernameError} </br>
        </font>
        Account Email: <input type="email" name="txtUsername" value="${requestScope.DTO.username}" id="userBox"/> </br>
        <font color="red">
        ${requestScope.INVALID.passwordError} </br> 
        </font>
        Password: <input type="password" name="txtPassword" id="passBox"/> </br>
        <font color="red">
        ${requestScope.INVALID.confirmError} </br>
        </font>
        Confirm Password: <input type="password" name="txtConfirmPassword" id="conBox"/> </br>
        <font color="red">
        ${requestScope.INVALID.fullnameError} </br>
        </font>
        Fullname: <input type="text" name="txtFullname" value="${requestScope.DTO.fullname}" id="fullBox"/> </br>
        <input type="submit" name="action" value="Register"/> 
    </form>
    <form method="POST" action="index.jsp">
        <button type="submit">Back to Login Page</button>
    </form>
</body>
</html>
