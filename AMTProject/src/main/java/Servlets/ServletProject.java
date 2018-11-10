package Servlets;

import Database.ProjectInterface;
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

    @EJB(beanName = "ProjectDAO")
    ProjectInterface projectDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProject - doGet]");

        User user = (User)request.getSession().getAttribute("user");

        System.out.println("[ServletProject - doGet] user.email - " + user.getEmail());

        ArrayList<Project> projects = new ArrayList<Project>();
        projects = projectDAO.getAllProjectByUser(user.getEmail()) ;

        request.setAttribute("projects", projects);
        request.getRequestDispatcher("/WEB-INF/pages/project/project.jsp").forward(request, response);
    }

}