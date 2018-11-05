package Servlets;

// Project
import Database.UserDAO;
import Model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

// Externe
import javax.ejb.EJB;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServletLogin extends javax.servlet.http.HttpServlet {

    @EJB
    private UserDAO userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletLogin - doPost]");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check values if not empty
        String errorMessage = "Error in EMAIL/PASSWORD";

        if (email == null || email.isEmpty()) {
            email = "";
        } else {
            System.out.println("[ServletLogin - doPost] email - " + email);
        }

        if (password == null || password.isEmpty()) {
            password = "";
        } else {
            System.out.println("[ServletLogin - doPost] password - " + password);
        }


        boolean userExist = userDao.findIfUserExist(email, password);
        System.out.println("[ServletLogin - doPost] userExist - " + userExist);

        if (!userExist) {
            System.out.println("[ServletLogin - doPost] No Access -");
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            System.out.println("[ServletLogin - doPost] Access -");
            User user = userDao.getUserWithID(email);

            System.out.println("[ServletLogin - doPost] home - firstname - " + user.getFirstname());
            System.out.println("[ServletLogin - doPost] home - lastname  - " + user.getLastname());
            System.out.println("[ServletLogin - doPost] home - email     - " + user.getEmail());
            System.out.println("[ServletLogin - doPost] home - address   - " + user.getAddress());
            System.out.println("[ServletLogin - doPost] home - zip       - " + user.getZip());
            System.out.println("[ServletLogin - doPost] home - country   - " + user.getCountry());
            System.out.println("[ServletLogin - doPost] home - admin     - " + user.getAdmin());
            System.out.println("[ServletLogin - doPost] home - enable    - " + user.getEnable());
            System.out.println("[ServletLogin - doPost] home - reset     - " + user.isReset());

            request.getSession().setAttribute("user", user);

            if (user.isReset()) {
                System.out.println("[ServletLogin - doPost] Call NEWPASSWORD" );
                response.sendRedirect("setpassword");
            } else {
                System.out.println("[ServletLogin - doPost] Call HOME" );
                response.sendRedirect("home");
            }
        }
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletLogin - doGet]");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }





}