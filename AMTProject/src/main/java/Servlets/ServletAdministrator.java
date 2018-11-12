package Servlets;

import Database.UserInterface;
import Model.User;
import Utils.*;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class ServletAdministrator extends javax.servlet.http.HttpServlet {

    @EJB(beanName ="UserDAO")
    UserInterface userDao;

    @EJB(beanName = "SendEmail")
    SendEmailInterface se;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletAdministrator - doGet]");

        List<User> usersEmailEnable = userDao.getAllUsersEmailAndStatus();

        for (User user : usersEmailEnable) {
            System.out.println("[doGet] - " + user.getEmail());
            System.out.println("[doGet] - " + user.isEnable());
        }

        request.setAttribute("_users", usersEmailEnable);

        request.getRequestDispatcher("/WEB-INF/pages/administrator/administrator.jsp").forward(request, response);
    }

    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletAdministrator - doPost]");

        String ckEnable = request.getParameter("ckEnable");
        String ckDelete = request.getParameter("ckDelete");
        String ckResetPW = request.getParameter("ckResetPW");

        String errorMessage = "";

        System.out.println("[ServletHome - doPost] ckEnable - " + ckEnable);
        System.out.println("[ServletHome - doPost] ckDelete - " + ckDelete);
        System.out.println("[ServletHome - doPost] ckResetPW - " + ckResetPW);

        // Enable
        if (ckEnable != null){
            System.out.println("[ServletHome - doPost] email - " + ckEnable);
            boolean enableSQL = userDao.enableUser(ckEnable);
            System.out.println("[ServletHome - doPost] enableSQL - " + enableSQL);
            if (!enableSQL) {
                errorMessage += "Account has been not enabled \n";
            }
        } else {
            if (userDao.disableUser(ckEnable)) {
                errorMessage += "Account has been not disabled \n";
            }
        }

        // Delete
        if (ckDelete != null){
            boolean deleteSQL = userDao.deletUser(ckDelete);
            System.out.println("[ServletHome - doPost] deleteSQL - " + deleteSQL);
            if(!deleteSQL) {
                errorMessage += "Account has been not deleted \n";
            }
        }

        // Reset Password
        if (ckResetPW != null) {
            // Generate new password
            GenratePassword passwordGenerator = new GenratePassword.PasswordGeneratorBuilder()
                    .useDigits(true)
                    .useLower(true)
                    .useUpper(true)
                    .build();
            String password = passwordGenerator.generate(8);

            boolean updateSQL = userDao.updateUserPassword(ckResetPW, password);
            System.out.println("[ServletHome - doPost] updateSQL - " + updateSQL);
            if(!updateSQL) {
                errorMessage += "Password has not been rested \n";
            } else {

                User user = userDao.getUserWithID(ckResetPW);

                // send confirmation Email
                String message = "Hi " + user.getFirstname() + " " + user.getLastname() + " ,\n " +
                        "Your Password account has been reset. \n" +
                        "Here is your new password : \n" +
                        "Password  : " + user.getPassword() + "\n\n" +
                        "Have a nice and sunny day \n";
                System.out.println(message);
                String title = "[AMT-Project-2018] - Password Reset";
                se.sendEmail(ckResetPW, title, message);
                response.sendRedirect("login");
            }
        }
        response.sendRedirect("administrator");
    }
}
