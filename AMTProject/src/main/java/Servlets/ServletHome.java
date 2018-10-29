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


        String errorMessage = "";
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        User user = (User)request.getSession().getAttribute("user");

        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");


        System.out.println("[ServletHome - doPost] firstname - " + firstname);
        System.out.println("[ServletHome - doPost] lastname  - " + lastname);
        System.out.println("[ServletHome - doPost] email     - " + user.getEmail());
        System.out.println("[ServletHome - doPost] password  - " + password);
        System.out.println("[ServletHome - doPost] address   - " + address);
        System.out.println("[ServletHome - doPost] zip       - " + zip);
        System.out.println("[ServletHome - doPost] country   - " + country);

        boolean queryOk = userDao.update(firstname, lastname, user.getEmail(), password, address, zip, country);


        System.out.println("[ServletHome - doPost] queryOk = " + queryOk);

        if (queryOk) {
            errorMessage = "Update Success";
            System.out.println("[ServletHome - doPost] end -" + errorMessage);

            User newUser = userDao.getUserWithID(user.getEmail());
            request.getSession().setAttribute("user", newUser);

            request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
        } else {
            errorMessage = "Error Update";
            System.out.println("[ServletHome - doPost] end -" + errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
        }
    }


}