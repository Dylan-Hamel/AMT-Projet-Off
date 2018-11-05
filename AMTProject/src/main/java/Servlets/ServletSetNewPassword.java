package Servlets;

import Database.UserDAO;
import Database.UserInterface;
import Model.User;
import Utils.GenratePassword;
import Utils.SendEmail;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class ServletSetNewPassword extends javax.servlet.http.HttpServlet {


    @EJB(beanName ="UserDAO")
    UserInterface userDao;

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletSetNewPassword - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/setpassword/setpassword.jsp").forward(request, response);

    }

    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletSetNewPassword - doPost]");

        String errorMessage = "";
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        System.out.println("[ServletSetNewPassword - doPost] password1 - " + password1);
        System.out.println("[ServletSetNewPassword - doPost] password2 - " + password2);

        // Check if passwords are same
        if (!password1.equals(password2)) {
            System.out.println("[ServletSetNewPassword - doPost] Error Password not same");

            errorMessage += "Passwords are not the same";
            request.setAttribute("errorMessage", errorMessage);

            request.getRequestDispatcher("/WEB-INF/pages/setpassword/setpassword.jsp").forward(request, response);

        } else {

            User user = (User)request.getSession().getAttribute("user");
            System.out.println("[ServletSetNewPassword - doPost] user.email - " + user.getEmail());

            if (userDao.updateUserPassword(user.getEmail(), password1)) {

                userDao.setUserResetTo0(user.getEmail());

                System.out.println("[ServletSetNewPassword - doPost] Password Updated - ");

                // Send new password by email
                String message = "New Password : " + password1;
                String title = "[AMT-Project-2018] - New Password";

                SendEmail se = new SendEmail(user.getEmail(), title, message);

                System.out.println("[ServletLogin - doPost] Call HOME" );
                response.sendRedirect("home");

            } else {
                errorMessage += "Error password not updated";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pages/setpassword/setpassword.jsp").forward(request, response);

            }
        }
    }
}