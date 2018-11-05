package Servlets;

import Database.ProjectDAO;
import Database.ProjectInterface;
import Database.UserDAO;
import Model.Project;
import Model.User;
import Utils.GenerateAPIKey;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServletProjectAdd extends javax.servlet.http.HttpServlet {

    @EJB(beanName = "ProjectDAO")
    ProjectInterface projectDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletRegister - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/projectadd/projectadd.jsp").forward(request, response);
    }

    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletProjectAdd - doPost]");

        String errorMessage = "";
        boolean registerFormOK = true;


        // Retrieve value from register form
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (description == null || description.isEmpty()) {
            description = "";
            errorMessage += "Error With description \n";
            registerFormOK = false;
        }

        if (name == null || name.isEmpty()) {
            name = "";
            errorMessage += "Error With name";
            registerFormOK = false;
        }

        System.out.println("[ServletProjectAdd - doPost] " + name);
        System.out.println("[ServletProjectAdd - doPost] " + description);

        if (registerFormOK) {

            // Check if project name doesn't exist
            // name is primary key
            if (projectDAO.checkIfProjectExist(name)) {
                System.out.println("[ServletProjectAdd - doPost] checkIfProjecExist  = True");
                errorMessage = "Error " + name + " already exists";
                request.setAttribute("errorMessage", errorMessage);
                request.setAttribute("name", name);
                request.setAttribute("description", description);

                request.getRequestDispatcher("/WEB-INF/pages/projectadd/projectadd.jsp").forward(request, response);
            } else {

                // Generate API informations
                GenerateAPIKey gak = new GenerateAPIKey();
                String api_key = "";
                String api_secret = "";
                try {

                    ArrayList<String> allAPIKey = projectDAO.getAllAPIKey();
                    ArrayList<String> allAPISecret = projectDAO.getAllAPISecret();


                    api_key = gak.generate(128);
                    api_secret = gak.generate(192);

                    // We check if key is unique
                    while (allAPIKey.contains(api_key)) {
                        api_key = gak.generate(128);
                    }

                    // We check if key if secret is unique
                    while (allAPISecret.contains(api_secret)) {
                        api_secret = gak.generate(192);
                    }


                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();

                    // if error during generation => return to page
                    errorMessage = "Error with API generation";
                    request.setAttribute("errorMessage", errorMessage);
                    request.setAttribute("name", name);
                    request.setAttribute("description", description);

                    request.getRequestDispatcher("/WEB-INF/pages/projectadd/projectadd.jsp").forward(request, response);
                }

                User user = (User) request.getSession().getAttribute("user");

                // 1. insert into project
                // if ok
                // insert into join table users<->project
                if(projectDAO.insertProjet(name, description, api_key, api_secret))
                    if (projectDAO.insertProjetUser(user.getEmail(), name)) {
                        System.out.println("[ServletProjectAdd - doPost] insertOK");
                        response.sendRedirect("project");

                    }
                }
        } else {
            // if form isn't valid

            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("name", name);
            request.setAttribute("description", description);


            request.getRequestDispatcher("/WEB-INF/pages/projectadd/projectadd.jsp").forward(request, response);
        }



    }

}