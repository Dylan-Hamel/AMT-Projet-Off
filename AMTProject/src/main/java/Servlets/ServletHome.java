package Servlets;

import Database.UserDAO;
import Model.User;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;


public class ServletHome extends javax.servlet.http.HttpServlet {

    @EJB
    private UserDAO userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletHome - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
    }


    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletHome - doPost] Start");

        String errorMessage;

        System.out.println("[ServletHome - doPost] firstname - " + request.getAttribute("firstname"));
        System.out.println("[ServletHome - doPost] lastname  - " + request.getAttribute("lastname"));
        System.out.println("[ServletHome - doPost] email     - " + request.getAttribute("email"));
        System.out.println("[ServletHome - doPost] password  - " + request.getAttribute("password"));
        System.out.println("[ServletHome - doPost] address   - " + request.getAttribute("address"));
        System.out.println("[ServletHome - doPost] zip       - " + request.getAttribute("zip"));
        System.out.println("[ServletHome - doPost] country   - " + request.getAttribute("country"));

        boolean queryOk = userDao.update((String) request.getAttribute("firstname"), (String) request.getAttribute("lastname"),
                (String) request.getAttribute("email"), (String) request.getAttribute("password"),
                (String) request.getAttribute("address"), (String) request.getAttribute("zip"),
                (String) request.getAttribute("country"));

        request.setAttribute("firstname", (String) request.getAttribute("firstname"));
        request.setAttribute("lastname", (String) request.getAttribute("lastname"));
        request.setAttribute("email", (String) request.getAttribute("email"));
        request.setAttribute("password", (String) request.getAttribute("password"));
        request.setAttribute("address", (String) request.getAttribute("address"));
        request.setAttribute("zip", (String) request.getAttribute("zip"));
        request.setAttribute("country", (String) request.getAttribute("country"));

        System.out.println("[ServletHome - doPost] queryOk = " + queryOk);

        if (queryOk) {
            errorMessage = "Update Success";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
        } else {
            errorMessage = "Error Update";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
        }
    }


}
