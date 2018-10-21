package Servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;

public class ServletProject extends javax.servlet.http.HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProject - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/project/project.jsp").forward(request, response);
    }

}