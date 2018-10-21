<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form method="post" action="/mvc-simple/login">
    Login: </br><input type="text" name="username" value="<c:out value="${username}"/>">
    <br>
    Password: </br><input type="text" name="email" value="<c:out value="${email}"/>">
    <br>
    Password: </br><input type="text" name="password" value="<c:out value="${password}"/>">
    <br>
    Company: </br><input type="text" name="company" value="<c:out value="${company}"/>">
    <br>
    <input type="submit" value="Submit">
</form>
<form method="get" action="/mvc-simple/signin">
    <div class="error-msg">
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </div>
</form>
</body>
</html>
