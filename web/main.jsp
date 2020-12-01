<%-- 
    Document   : main.jsp
    Created on : Sep 20, 2020, 1:58:00 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <!-- MDB icon -->
        <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Google Fonts Roboto -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Material Design Bootstrap -->
        <link rel="stylesheet" href="css/mdb.min.css">
        <!-- MDBootstrap Datatables  -->
        <link href="css/addons/datatables.min.css" rel="stylesheet">
    <body style="background-color:yellowgreen"/>
    <style>
        form {
            text-align: center;
        }
        #btn {
            float: right;
        }
        table{
            border:5px solid black;
            margin-left:auto;
            margin-right:auto;
        }
        h1{
            margin: 0;
            display: inline-block;
        }
        h2{
            text-align: center;
        }
        #searchBox{
            width: 500px
        }
    </style>
</head>
<body>
    <c:if test="${empty sessionScope.EMAIL}" var="login"> 
        <h1>Welcome to our Mini Social Network</h1> <form id="btn" action="signup.jsp" method="POST"> <button>Sign Up</button> </form> <form id="btn" action="index.jsp" method="POST"> <button>Sign In</button> </form> 
    </c:if>
    <c:if test="${!login}">
        <h1>Welcome, ${sessionScope.FULLNAME}</h1> <form id="btn" action="MainController" method="POST"> <input type="submit" name="action" value="Logout"/> </form>
        </c:if>
    <form action="postarticle.jsp" method="POST">
        <input type="hidden" value="${sessionScope.EMAIL}"/>
        <button>Post Article</button> 
    </form>
    <form action="MainController" method="POST">
        Search Article: <input type="text" name="txtSearch" value="${param.txtSearch}" id="searchBox"/> 
        <input type="submit" value="Search Article" name="action"/>
        <font color="red" style="text-align: center;">
        </br>${requestScope.WARN}
        </font>
        <c:if test="${!login}">
            <c:if test="${not empty requestScope.NOTIFICATION}" var="notificateList">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Notifications</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="notificate" items="${requestScope.NOTIFICATION}">
                            <c:url value="DetailsController" var="notificateDetailLink">
                                <c:param name="action" value="Detail"/>
                                <c:param name="articleID" value="${notificate.articleID}"/>
                            </c:url>
                            <tr>
                                <td>
                                    <a href="${notificateDetailLink}">
                                        <c:if test="${notificate.notifyStatus eq 'LIKE'}">
                                            <font style="font-size:20px">${notificate.notifyDate}</font><br/>
                                            <b>${notificate.accountEmail}</b> liked your article <b>${notificate.tittle}</b>.
                                        </c:if>

                                        <c:if test="${notificate.notifyStatus eq 'DISLIKE'}">
                                            <font style="font-size:20px">${notificate.notifyDate}</font><br/>
                                            <b>${notificate.accountEmail}</b> dislike your article <b>${notificate.tittle}</b>.
                                        </c:if>

                                        <c:if test="${notificate.notifyStatus eq 'COMMENT'}">
                                            <font style="font-size:20px">${notificate.notifyDate}</font><br/>
                                            <b>${notificate.accountEmail}</b> commented on your article <b>${notificate.tittle}</b>.
                                        </c:if>
                                    </a>
                                </td>

                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="submit" name="action" value="Click to Remove"/>
                                        <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                        <input type="hidden" name="txtNotificateID" value="${notificate.ID}"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!notificateList}">
                <h2>No new notification right now.</h2>
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.INFO}" var="listCheck">
            <table border="1" id="myTable">
                <thead>
                    <tr>
                        <th><font style="font-weight: bold">All Articles</font></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="article" items="${requestScope.INFO}">
                        <tr>
                            <td>                            
                                <c:url var="detailLink" value="DetailsController">
                                    <c:param name="action" value ="Details"/>
                                    <c:param name="articleID" value="${article.articleID}"/>
                                </c:url>
                                <h2>Tittle: <a href="${detailLink}">${article.articleTittle}</a></h2>
                                <h3>Description: ${article.articleDescription}</h3>
                                <img src="${article.articleImage}"height="600" width="800"/>
                                <h4>Posted on: ${article.articleDate}</h4>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>   
        </c:if>
        <c:if test="${!listCheck}">
            <h2>No Article Found</h2>
        </c:if>
    </form>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
    <!-- MDBootstrap Datatables  -->
    <script type="text/javascript" src="js/addons/datatables.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#myTable').DataTable({
                "paging": true,
                "pagingType": "first_last_numbers",
                "ordering": false,
                "searching": false
            });
            $('.dataTables_length').addClass('bs-select');
        });
    </script>
</body>
</html>



