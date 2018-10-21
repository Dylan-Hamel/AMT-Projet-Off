# Structure du Pojet

Regarder l'image ```./Install/Different_Servlets.jpg```



Pour chaque page que l'on veut accéder, il faut créer une servlet.

Toutes ces servlets sont dans : ```src/main/java/Servlets```

Sous la forme suivante :

```java
package Servlets;

import javax.servlet.ServletException;
import java.io.IOException;

public class ServletAdministrator extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletAdministrator - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/administrator/administrator.jsp").forward(request, response);
    }
}
```



Il faut ensuite créer une entrée pour chaque Servlet dans le fichier```src/main/webapp/WEB-INF/web.xml```

```xml
  <servlet>
    <servlet-name>ServletAdministrator</servlet-name>
    <servlet-class>Servlets.ServletAdministrator</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>ServletAdministrator</servlet-name>
    <url-pattern>/administrator</url-pattern>
  </servlet-mapping>
```



De plus, nous passons par un "SecurityFilter" qui se trouve dans ```src/main/java/Filter/SecurityFilter```

```java
package Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // Don't filter resources and registration page
        if (request.getRequestURI().matches
                (".*(ttf|eot|svg|woff|jsp|ico|css|jpg|png|gif|js|register|login|home|profil|project|administrator|logout|resetpassword|)$")) {
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("SecurityFilter => Resources or basic pages asked");
            return;
        }
    }


    @Override
    public void destroy() {

    }
}
```

Il faut que les pages que l'on veut accéder soit dans la ligne 

```java
if (request.getRequestURI().matches(".*
```



Exemple, pour arriver sur la page de Register, on va utiliser le lien suivant 

```http://localhost:8080/AMT-Projet/register```

 on va devoir mettre 

```java
if (request.getRequestURI().matches(".*(jsp|ico|css|jpg|png|gif|js|register)$"))
```

```jsp|ico|css|jpg|png|gif|js``` correspond à tous les fichiers que l'on veut autorisés a être chargés sur la page.

Ensuite, dans la Class Servlet, il faut implémenter les fonctions "doGet", doPost.

Le SecurityFilter est dans le ```web.xml```

```xml
<filter>
  <filter-name>SecurityFilter</filter-name>
  <filter-class>Filter.SecurityFilter</filter-class>
</filter>
<filter-mapping>
  <filter-name>SecurityFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```



C'est également dans cette classe qu'il faudra travailler pour "bloquer" les accès aux pages 

* administrator
* home
* Etc.

si on n'est pas connecté. Il faudra utiliser cette classe

```java
import javax.servlet.http.HttpSession;
```



## Accès aux pages

Se fait via 

```
http://localhost:8080/AMT-Projet/register
```

et non 

```http://localhost:8080/AMT-Projet/register.jsp
http://localhost:8080/AMT-Projet/register.jsp
```



Dans le fichier HTML dans les "form" mettre :

```html
<form method="post" action="/AMT-Projet/login">
```

Dans les Servlets mettre le fichier avec l'extension

```java
@Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletLogin - doGet]");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
```

On va appeler la servlet à chaque fois que l'on veut atteindre une page et c'est la servlet qui va nous afficher le bon fichier.



le nom du projet ```/AMT-Projet/``` est définit dans le ```pom.xml```

```xml
<name>AMT-Projet</name>
```



## Tips

Si la page Web se charge mal, il faut remplacer les liens pour qu'ils soient chargé au bonne endroit

```html
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
```

Utiliser la variable 

```
${pageContext.request.contextPath}
```

et mettre les en-tête

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
```

Il se peut que les fichiers css ne se chargent pas correctement, dans ce cas utiliser un include JSP à la place

```html
    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/css/default.css"/>
    </style>
    <style type="text/css">
        <jsp:include page="/WEB-INF/pages/home/css/fonts.css"/>
    </style>
```



