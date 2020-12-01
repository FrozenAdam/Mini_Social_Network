<%-- 
    Document   : index
    Created on : Sep 16, 2020, 11:07:10 AM
    Author     : theFrozenAdam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    <body style="background-color:yellowgreen"/>
    <style>
        h1 {
            text-align: center;
        }
        h2{
            text-align: center;
        }
        form{
            text-align: center;
        }
        div{
            text-align: center;
        }
        #usernameBox, #passwordBox{
            width: 300px;
        }
    </style>
    <script>
        function validateLogin() {
            var x = document.forms["loginForm"]["txtUsername"].value;
            var y = document.forms["loginForm"]["txtPassword"].value;
            if (x === "" || y === "") {
                alert("Username and Password must be filled");
                return false;
            }
        }
    </script>
</head>
<body>
    <h1>Mini Social Network</h1>
    <h2>Login</h2>
    <div>
        <font color='red'>
        ${requestScope.SUCCESS}
        </font>
        <font color="red">
        ${requestScope.ERRORNOTE}
        </font>
    </div>
    <form action="MainController" method="POST" name="loginForm" onsubmit="return validateLogin()">
        Username: <input type="text" name="txtUsername" value="${param.txtUsername}" id="usernameBox"/> </br>
        Password: <input type ="password" name="txtPassword" id="passwordBox"/> </br>
        <input type="submit" name ="action" value="Login"/>
    </form>
    <form method="POST" action="signup.jsp">
        <button type="submit">Sign Up For New Users</button>
    </form>
    <form method="POST" action="MainController">
        <input type="hidden" name="action" value="Search Article"/>
        <input type="hidden" name="txtSearch" value=""/>
        <button type="submit">To Main Page</button>
    </form>
</body>
</html>
