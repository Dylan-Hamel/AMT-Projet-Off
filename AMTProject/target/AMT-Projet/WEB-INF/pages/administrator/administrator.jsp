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
    <title>AMT-Project - Manager Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>

<body>

<div class="container">
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

    <div>
        <br>
        <br>
        <br>
        <div class="header">
            <h1 class="text-center">Manager Users</h1>
            <br>
        </div>


        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">email</th>
                <th scope="col">Status</th>
                <th scope="col">Action</th>
                <th scope="col">Reset Password</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${_users}" var="u">
                <tr>
                    <form method="POST" action="/AMT-Projet/administrator">
                        <th>${u.email}</th>
                        <td width="30%"><input type="hidden" name="email" value="${u.email}" ><input type="checkbox" name="ckEnable" value="ckEnable" ${u.enable == true ? 'checked' : ''}> isEnable</td>
                        <td width="20%" class="text-center">
                            <input ${u.reset == true ? 'type="hidden"' : 'type="checkbox"'} name="ckResetPW" value="ckResetPW" > Reset Password
                            </br>
                            <input type="checkbox" name="ckDelete" value="ckDelete" > Delete
                        </td>
                        <td width="20%" class="text-center">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit changes</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
</body>

</html>
<!-- end document-->