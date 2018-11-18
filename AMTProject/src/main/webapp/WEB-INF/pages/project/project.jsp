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
    <title>AMT-Project - Project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>

<body>

<div class="container">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" id="home" href="/AMT-Projet/home">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" id="project" href="/AMT-Projet/project">Projects<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" id="administrator" href="/AMT-Projet/administrator">Manage User</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" id="logout" href="/AMT-Projet/logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div>
        <br>
        <br>
        <br>
        <div class="header">
            <h1 class="text-center">Your projects</h1>
            <br>
            <form method="get" action="/AMT-Projet/projectadd">
                <div class="text-center">
                    <button id="bAddApp" class="btn btn-primary" data-toggle="modal" data-target="#appModal" type="submit">Create a new project</button>
                </div>
            </form>
            <br>
        </div>


        <table class="table" id="dataTable">
            <thead class="thead-light">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">API Key</th>
                <th scope="col">API Secret</th>
                <th scope="col">Action</th>
                <th scope="col">Details</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${projects}" var="proj">
                <tr>
                    <th>${proj.name}</th>
                    <td width="30%">${proj.description}</td>
                    <td width="15%">${proj.api_key}</td>
                    <td width="20%">${proj.api_secret}</td>
                    <td width="20%" class="text-center">
                        <a href="${pageContext.servletContext.contextPath}/project?action=delete&proj_name=${proj.name}" class="dropdown-item">Delete</a>
                    </td>
                    <td width="20%" class="text-center">
                        <a class="nav-link disabled" href="/AMT-Projet/projectedit?proj_name=${proj.name}&proj_descr=${proj.description}&proj_api_key=${proj.api_key}&proj_api_secret=${proj.api_secret}">Edit</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <select id="nbProjects" onchange="changeNbOfProjects(this.value)">
            <option value="5" ${nbRecords == 5 ? 'selected' : ''}>5</option>
            <option value="10" ${nbRecords == 10 ? 'selected' : ''}>10</option>
            <option value="15" ${nbRecords == 15 ? 'selected' : ''}>15</option>
        </select>
        Page <p id="pageNum">${pageNum}</p> : ${numFirst}-${numLast} / ${nbProjects}
        <c:if test="${numFirst > 1}">
            <button id="dataTablePrevious" onclick="changePage(${pageNum - 2})">Page précédente</button>
        </c:if>
        <c:if test="${nbProjects > numLast}">
            <button id="dataTableNext" onclick="changePage(${pageNum})">Page suivante</button>
        </c:if>
    </div>
</div>

<script>
    function changeNbOfProjects(val) {
        window.location="project?nbRecords="+val+"&numPage=0";
    }
    function changePage(val) {
        var e = document.getElementById("nbProjects");
        var nbProjects = e.options[e.selectedIndex].value;
        window.location="project?nbRecords="+nbProjects+"&numPage="+val;
    }
</script>

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