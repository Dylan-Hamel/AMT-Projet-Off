package Servlets;

import Database.ProjectInterface;
import Model.Project;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class ServletProjectEdit extends javax.servlet.http.HttpServlet {

    @EJB(beanName = "ProjectDAO")
    ProjectInterface projectDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProjectEdit - doGet]");

        String name     = request.getParameter("proj_name");
        String descr    = request.getParameter("proj_descr");
        String api_key  = request.getParameter("proj_api_key");
        String api_secr = request.getParameter("proj_api_secret");

        System.out.println("[ServletProjectEdit - doGet] name - " + name);
        System.out.println("[ServletProjectEdit - doGet] descr - " + descr);
        System.out.println("[ServletProjectEdit - doGet] api_key - " + api_key);
        System.out.println("[ServletProjectEdit - doGet] api_secr - " + api_secr);


        request.setAttribute("name", name);
        request.setAttribute("descr", descr);
        request.setAttribute("apikey", api_key);
        request.setAttribute("apisecr", api_secr);

        request.getRequestDispatcher("/WEB-INF/pages/projectedit/projectedit.jsp").forward(request, response);
    }

    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProjectEdit - doPost]");

        String errorMessage = "";

        String name = request.getParameter("project_name");
        String newDescription = request.getParameter("description");
        String api_key = request.getParameter("api_key");
        String api_secret = request.getParameter("api_secret");

        System.out.println("[ServletProjectEdit - doPost] name - " + name);
        System.out.println("[ServletProjectEdit - doPost] api_key - " + api_key);
        System.out.println("[ServletProjectEdit - doPost] api_secret - " + api_secret);


        if (!projectDAO.updateProjectDescription(name, newDescription)) {
            errorMessage = "Update Failed";

            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("apisecret", api_secret);
            request.setAttribute("apikey", api_key);
            request.setAttribute("name", name);
            request.setAttribute("description", "");

            request.getRequestDispatcher("/WEB-INF/pages/projectedit/projectedit.jsp").forward(request, response);
        }

        System.out.println("[ServletProjectEdit - doPost] Call PROJECT" );
        response.sendRedirect("project");

    }
}