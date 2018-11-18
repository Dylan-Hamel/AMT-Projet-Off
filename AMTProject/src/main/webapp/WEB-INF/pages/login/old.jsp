<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form method="post" action="/AMT-Projet/login/">
    Login: </br><input type="text" name="email" value="<c:out value="${email}"/>">
    <br>
    Password: </br><input type="text" name="password" value="<c:out value="${password}"/>">
</form>
<form method="get" action="/AMT-Projet/login">
    <div class="error-msg">
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </div>
</body>
</html>
