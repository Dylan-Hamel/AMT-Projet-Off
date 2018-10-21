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
