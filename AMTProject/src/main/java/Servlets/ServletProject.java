package Servlets;

import Database.ProjectDAO;
import Database.UserDAO;
import Model.Project;
import Model.User;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServletProject extends javax.servlet.http.HttpServlet {

    @EJB
    private ProjectDAO projectDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProject - doGet]");
        System.out.println("[ServletProject - doGet] request.getParameter(\"action\") = " + request.getParameter("action"));

        String req = "";

        if (request.getParameter("action") != null)
            req = request.getParameter("action");

        if (req.equals("delete")) {

            System.out.println("[ServletProject - doGet] DELETE");
            System.out.println("[ServletProject - doGet] proj_name = " + (request.getParameter("proj_name")));

            projectDAO.deleteProjectFromJoinTableAndProject(request.getParameter("proj_name"));

        }


        User user = (User)request.getSession().getAttribute("user");

        System.out.println("[ServletProject - doGet] user.email - " + user.getEmail());

        ArrayList<Project> projects = new ArrayList<Project>();
        projects = projectDAO.getAllProjectByUser(user.getEmail()) ;

        request.setAttribute("projects", projects);
        request.getRequestDispatcher("/WEB-INF/pages/project/project.jsp").forward(request, response);
    }

}