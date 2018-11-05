package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletLogout extends javax.servlet.http.HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Desactivate sessions
        request.getSession().invalidate();
        System.out.println("[ServletLogout - doGet]");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
