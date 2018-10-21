package Servlets;

// Project
import Database.UserDAO;
import com.sun.org.apache.xpath.internal.operations.Bool;

// Externe
import javax.ejb.EJB;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            // Verify email address
            System.out.println("[ServletLogin - doPost] email - " + email);
            /*try {
                if (email == null)
                    email = "";
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
            } catch (AddressException ex) {
                errorMessage += "Error in email \r\n";
            }
            */
        }

        if (password == null || password.isEmpty()) {
            password = "";
        } else {
            System.out.println("[ServletLogin - doPost] password - " + password);
        }


        boolean userExist = userDao.findIfEnableUserExist(email, password);
        System.out.println("[ServletLogin - doPost] userExist - " + userExist);

        if (!userExist) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletLogin - doGet]");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }





}