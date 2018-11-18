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

        int nbOfRecords = 10;
        if(request.getParameter("nbRecords") != null){
            nbOfRecords = Integer.parseInt(request.getParameter("nbRecords"));
        }
        int numPage = 0;
        if(request.getParameter("numPage") != null){
            numPage = Integer.parseInt(request.getParameter("numPage"));
        }

        ArrayList<Project> projects = new ArrayList<Project>(nbOfRecords);

        //projects = projectDAO.getAllProjectByUser(user.getEmail());

        int nbOfProjects = projectDAO.countProjectByUser(user.getEmail());
        int numFirst = nbOfRecords*numPage + 1;
        if(nbOfProjects < nbOfRecords*numPage){
            projects = projectDAO.getProjectByUser(user.getEmail(), nbOfRecords, nbOfProjects - nbOfRecords);
            numFirst = nbOfProjects - nbOfRecords + 1;
            numPage = nbOfProjects / nbOfRecords;
        }
        else {
            projects = projectDAO.getProjectByUser(user.getEmail(), nbOfRecords, nbOfRecords * numPage);
        }

        int numLast = nbOfRecords*numPage + nbOfRecords;
        if(numLast > nbOfProjects){
            numLast = nbOfProjects;
        }

        request.setAttribute("projects", projects);
        request.setAttribute("nbProjects", nbOfProjects);
        request.setAttribute("numFirst", numFirst);
        request.setAttribute("numLast", numLast);
        request.setAttribute("nbRecords", nbOfRecords);
        request.setAttribute("pageNum", numPage+1);
        request.getRequestDispatcher("/WEB-INF/pages/project/project.jsp").forward(request, response);
    }

}