<%-- 
    Document   : details
    Created on : Sep 25, 2020, 10:45:06 AM
    Author     : theFrozenAdam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article Details</title>
    <body style="background-color:yellowgreen"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        table{
            border:5px solid black;
            margin-left:auto;
            margin-right:auto;
        }
        #backBtn{
            float: right;
        }
        #commentBox{
            width: 715px;
        }
        #deleteIcon{
            float: right;
        }
        #like-dislike{
            display: inline;
        }
    </style>
    <script>
        function validateComment() {
            var x = document.forms["commentForm"]["txtComment"].value;
            if (x === "") {
                alert("Comment must be fill");
                return false;
            }
        }
        function confirm_delete() {
            return confirm("Are you sure?");
        }
    </script>
</head>
<body>
    <c:if test="${empty sessionScope.EMAIL}">
        <c:redirect url="index.jsp"/>
    </c:if>
    <form id="backBtn"method="POST" action="SearchArticleController">
        <input type="hidden" value="" name="txtSearch"/>
        <button type="submit">Back to Main Page</button>
    </form>
    <table border="1">
        <tbody>
            <tr>
                <td>
                    <c:url var="articleDelete" value="MainController">
                        <c:param name="action" value="Delete Article"/>
                        <c:param name="articleID" value="${param.articleID}"/>
                        <c:param name="articlePostBy" value="${sessionScope.EMAIL}"/>
                    </c:url>
                    <c:if test="${requestScope.DTO.articlePostBy eq sessionScope.EMAIL}" var="check">
                        <h2>Tittle: ${requestScope.DTO.articleTittle}</a></h2> <a href="${articleDelete}" id="deleteIcon" onclick="return confirm_delete()" class="fa fa-trash"></a>
                        <h3>Description: ${requestScope.DTO.articleDescription}</h3>
                        <h4>Posted by: ${requestScope.DTO.articlePostBy}</h4> 
                        <h5>Posted on: ${requestScope.DTO.articleDate}</h5>
                        <img src="${requestScope.DTO.articleImage}"height="800" width="800"/> 
                    </c:if>

                    <c:if test="${!check}">
                        <h2>Tittle: ${requestScope.DTO.articleTittle}</a></h2>
                        <h3>Description: ${requestScope.DTO.articleDescription}</h3>
                        <h4>Posted by: ${requestScope.DTO.articlePostBy}</h4> 
                        <h5>Posted on: ${requestScope.DTO.articleDate}</h5>
                        <img src="${requestScope.DTO.articleImage}"height="600" width="800"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="like-dislike">
                        <form method="POST" action="MainController" style="display: inline;">
                            <input type="hidden" name="articleID" value="${param.articleID}"/>
                            <input type="hidden" name="accountEmail" value="${sessionScope.EMAIL}"/>
                            <input type="hidden" name="which" value="Like"/>
                            <button class="fa fa-thumbs-up" name="action" value="Emote">Like: ${requestScope.LIKES}</button>
                        </form>
                        <form method="POST" action="MainController" style="display: inline; float: right;">
                            <input type="hidden" name="articleID" value="${param.articleID}"/>
                            <input type="hidden" name="accountEmail" value="${sessionScope.EMAIL}"/>
                            <input type="hidden" name="which" value="Dislike"/>
                            <button class ="fa fa-thumbs-down" name="action" value="Emote">Dislike: ${requestScope.DISLIKES}</button>
                        </form>
                    </div>
                </td>
            </tr>
            <c:forEach var="comment" items="${requestScope.COMMENTS}">
                <tr>
                    <td style="background-color:#e5e5e5">
                        <c:url var="commentDelete" value="MainController">
                            <c:param name="action" value="Delete Comment"/>
                            <c:param name="articleID" value="${param.articleID}"/>
                            <c:param name="txtCommentPostBy" value="${sessionScope.EMAIL}"/>
                        </c:url>
                        <c:if test="${comment.commentPostBy eq sessionScope.EMAIL}" var="check">
                            <font style="font-weight: bold">${comment.name}</font> - ${comment.commentDate}:<br/> 
                            ${comment.commentContent} <a href="${commentDelete}" id="deleteIcon" onclick="return confirm_delete()" class="fa fa-trash"></a>
                            </c:if>

                        <c:if test="${!check}">
                            <font style="font-weight: bold">${comment.name}</font> - ${comment.commentDate}:<br/> 
                            ${comment.commentContent}<br/>
                        </c:if>
                    </td> 
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <form name="commentForm" action="MainController" onsubmit="return validateComment()" method="POST">
                        <input type="text" id="commentBox" name="txtComment"/>
                        <input type="hidden" name="txtCommentPostBy" value="${sessionScope.EMAIL}"/>
                        <input type="hidden" name="articleID" value="${param.articleID}"/>
                        <input type="submit" name="action" value="Comment"/>
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>

