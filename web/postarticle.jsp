<%-- 
    Document   : postarticle
    Created on : Sep 23, 2020, 4:44:26 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Article</title>
    <body style="background-color:yellowgreen"/>
    <style>
        h1{
            text-align: center;
        }
        form{
            text-align: center;
        }
        #tittle, #image{
            width: 300px;
        }
        #description{
            width: 260px;
            height: 250px;
        }
    </style>
    <script>
        function validatePost() {
            var x = document.forms["postArticleForm"]["txtTittle"].value;
            var y = document.forms["postArticleForm"]["txtDescription"].value;
            if (x === "" || y === "") {
                alert("Tittle is required\nDescription is required\n");
                return false;
            }
        }
    </script>
</head>
<body>
    <c:if test="${empty sessionScope.EMAIL}">
        <c:redirect url="index.jsp"/>
    </c:if>
    <h1>Post New Article</h1>
    <form action="MainController" method="POST" name="postArticleForm" onsubmit="return validatePost()">
        Article Tittle: <input type="text" name="txtTittle" id="tittle"/> </br>
        </br>
        Article Description: <input type="text" name="txtDescription" id="description"/> </br>
        </br>
        Article Image: <input type="text" name="txtImage" id="image"/> </br>
        <input type="hidden" name="txtPostBy" value="${sessionScope.EMAIL}"/>
        <input type="hidden" value="" name="txtSearch"/>
        <input type="submit" name="action" value="Post Article"/>
    </form>
    <form method="POST" action="SearchArticleController">
        <input type="hidden" value="" name="txtSearch"/>
        <button type="submit">Back to Main Page</button>
    </form>
</body>
</html>
