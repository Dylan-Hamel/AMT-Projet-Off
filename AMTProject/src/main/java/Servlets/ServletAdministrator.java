package Servlets;

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

public class ServletAdministrator extends javax.servlet.http.HttpServlet {

    @EJB
    private UserDAO userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletAdministrator - doGet]");

        List<User> usersEmailEnable = userDao.getAllUsersEmailAndStatus();

        System.out.println(usersEmailEnable);

        for (User user : usersEmailEnable) {
            System.out.println("[doGet] - " + user.getEmail());
            System.out.println("[doGet] - " + user.getEnable());
        }

        request.setAttribute("_users", usersEmailEnable);

        request.getRequestDispatcher("/WEB-INF/pages/administrator/administrator.jsp").forward(request, response);
    }
}
