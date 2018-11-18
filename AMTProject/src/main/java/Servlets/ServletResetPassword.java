package Servlets;

import Database.UserInterface;
import Utils.GenratePassword;
import Utils.SendEmail;
import Utils.SendEmailInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class ServletResetPassword extends javax.servlet.http.HttpServlet {

    @EJB(beanName ="UserDAO")
    UserInterface userDao;

    @EJB(beanName = "SendEmail")
    SendEmailInterface se;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletResetPassword - doPost]");

        String email = request.getParameter("email");
        String errorMessage = "";

        boolean update = false;

        if (email == null || email.isEmpty()) {
            System.out.println("[ServletResetPassword - doPost] Error Email Address");
            errorMessage = "Please enter your email address";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/resetpassword/resetpassword.jsp").forward(request, response);

        } else {
            System.out.println("[ServletResetPassword - userDAO start]");
            ArrayList<String> allEmailAdresses = (ArrayList<String>) userDao.getAllUsersEmailAddress();
            System.out.println("[ServletResetPassword - userDAO finish]");
            boolean emailFound = false;

            for (int i=0; i<allEmailAdresses.size(); i++) {
                if (allEmailAdresses.get(i).equals(email)) {
                    System.out.println("[ServletResetPassword - doPost] emailFound == True");
                    emailFound = true;

                    // Generate new password
                    GenratePassword passwordGenerator = new GenratePassword.PasswordGeneratorBuilder()
                            .useDigits(true)
                            .useLower(true)
                            .useUpper(true)
                            .build();
                    String password = passwordGenerator.generate(8);
                    System.out.println("[ServletResetPassword - doPost] new password = " + password);

                    // Send new password by email
                    String message = "New Password : " + password +  "\n" +
                            "This password must be changed at the next login";
                    String title = "[AMT-Project-2018] - New Password";

                    if (se.sendEmail(email, title, message)){

                        // Update password In DB
                        userDao.updateUserPassword(email, password);

                        update = userDao.setUserResetTo1(email);
                    }
                    break;
                }
            }

            System.out.println("[ServletResetPassword emailFound - ]" + emailFound);
            if (!emailFound) {
                errorMessage = "Email Address Not found";
            } else if (update){
                errorMessage = "New password has been send on your email address";
            } else {
                errorMessage = "Error password not updated";
            }
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/resetpassword/resetpassword.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletResetPassword - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/resetpassword/resetpassword.jsp").forward(request, response);

    }


}
