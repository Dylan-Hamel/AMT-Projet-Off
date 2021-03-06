<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>AMT-Project - Home</title>

    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/vendor/mdi-font/css/material-design-iconic-font.min.css"/>
    </style>
    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/vendor/font-awesome-4.7/css/font-awesome.min.css"/>
    </style>
    <!-- Icons font CSS-->
    <!-- <link href="${pageContext.request.contextPath}/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all"> -->
    <!-- <link href="${pageContext.request.contextPath}/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all"> -->
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/vendor/select2/select2.min.css"/>
    </style>
    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/vendor/datepicker/daterangepicker.css"/>
    </style>

    <style type="text/css">

        .container {
            max-width: 100% !important;
            padding-left: 0px !important;
            padding-right: 0px !important;
        }

    </style>

    <!-- Vendor CSS-->
    <!-- <link href="${pageContext.request.contextPath}/vendor/select2/select2.min.css" rel="stylesheet" media="all">-->
    <!--<link href="${pageContext.request.contextPath}/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">-->

    <!-- Main CSS-->
    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/css/main.css"/>
    </style>
    <!-- <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="all"> -->
</head>

<body>
<div class="container">

    <!-- <div id="header-wrapper">
    <div id="header" class="container">
        <div id="menu">
            <ul>
                <li class="current_page_item"><a href="#" accesskey="1" title="">Homepage</a></li>
                <li><a href="project" accesskey="2" title="">Project</a></li>
                <li><a href="administrator" accesskey="3" title="">Manage User</a></li>
                <li><a href="logout" accesskey="3" title="">Logout</a></li>
            </ul>
        </div>
    </div> -->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/AMT-Projet/home">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="/AMT-Projet/project">Projects</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="/AMT-Projet/administrator">Manage User</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="/AMT-Projet/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">

                    <h2 class="title">HOME - Profile Description</h2>
                    <h4> Modify your profil - ${user.email}</h4>
                    </br>
                    <form method="get" action="/AMT-Projet/home">
                        <div class="error-msg">
                            <c:if test="${not empty esrorMessage}">
                                <c:out value="${errorMessage}"/>
                            </c:if>
                        </div>
                    </form>
                    <form method="POST" action="/AMT-Projet/home">
                        <div class="row row-space">
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Firstname</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="text" id="firstname" name="firstname" value="<c:out value="${user.firstname}"/>">
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Lastname</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="text" id="lastname" name="lastname" value="<c:out value="${user.lastname}"/>">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Email</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="email" id="email" name="email" disabled="disabled" value="<c:out value="${user.email}"/>">
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Password</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4" type="password" name="password" value="<c:out value="${user.password}"/>">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Address</label>
                                    <input class="input--style-4" type="text" id="address" name="address" value="<c:out value="${user.address}"/>">
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Zip</label>
                                    <input class="input--style-4" type="text" id="zip" name="zip" value="<c:out value="${user.zip}"/>">
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-6">
                                <div class="input-group">
                                    <label class="label">Country</label>
                                    <input class="input--style-4" type="text" id="country" name="country" value="<c:out value="${user.country}"/>">
                                </div>
                            </div>
                        </div>
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- Jquery JS-->
    <script src="/WEB-INF/pages/home/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="/WEB-INF/pages/home/vendor/select2/select2.min.js"></script>
    <script src="/WEB-INF/pages/home/vendor/datepicker/moment.min.js"></script>
    <script src="/WEB-INF/pages/home/vendor/datepicker/daterangepicker.js"></script>

<!-- Main JS-->
<script src="/WEB-INF/pages/home/js/global.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->