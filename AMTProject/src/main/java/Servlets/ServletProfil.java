package Servlets;

import javax.servlet.ServletException;
import java.io.IOException;

public class ServletProfil extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProfil - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/profil/profil.jsp").forward(request, response);
    }
}
