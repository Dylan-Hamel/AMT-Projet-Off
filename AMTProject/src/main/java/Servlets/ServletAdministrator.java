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

        String email = request.getParameter("email");
        System.out.println("[ServletAdministrator - submited user - " + email);
        String ckEnable = request.getParameter("ckEnable");
        System.out.println("[ServletAdministrator - submited user - " + ckEnable);
        String ckDelete = request.getParameter("ckDelete");
        System.out.println("[ServletAdministrator - submited user - " + ckDelete);
        String ckResetPW = request.getParameter("ckResetPW");
        System.out.println("[ServletAdministrator - submited user - " + ckResetPW);

        String errorMessage = "";

        // Delete


        if (ckDelete != null){
            System.out.println("[ServletAdministrator - doPost] ckDelete - " + email);
            boolean deleteSQL = userDao.deletUser(email);
            if(!deleteSQL) {
                errorMessage += "Account has been not deleted \n";
            }else {
                User user = userDao.getUserWithID(email);

                // send confirmation Email
                String message = "Hi " + user.getFirstname() + " " + user.getLastname() + " ,\n " +
                        "Your account has been deleted.\n\n" +
                        "Have a nice day. \n";
                System.out.println(message);
                String title = "[AMT-Project-2018] - deleted account";
                SendEmail se = new SendEmail(email, title, message);
                response.sendRedirect("administrator");
            }
        }else{

            // Reset Password
            if (ckResetPW != null) {
                System.out.println("[ServletAdministrator - doPost] ckResetPW - " + email);
                // Generate new password
                GenratePassword passwordGenerator = new GenratePassword.PasswordGeneratorBuilder()
                        .useDigits(true)
                        .useLower(true)
                        .useUpper(true)
                        .build();
                String password = passwordGenerator.generate(8);

                boolean updateSQL = userDao.updateUserPassword(email, password);
                boolean enableSQL = userDao.enableUser(email);
                System.out.println("[ServletAdministrator - doPost] updateSQL - " + email);
                if(!updateSQL) {
                    errorMessage += "Password has not been rested \n";
                } else if (!enableSQL){
                    errorMessage += "User has not beenn enabled \n";
                }

                User user = userDao.getUserWithID(email);

                // send confirmation Email
                String message = "Hi " + user.getFirstname() + " " + user.getLastname() + " ,\n" +
                        "Your Password account has been reset. \n" +
                        "Here is your new password : \n" +
                        "Password  : " + user.getPassword() + "\n\n" +
                        "Have a nice and sunny day \n";
                System.out.println(message);
                String title = "[AMT-Project-2018] - Password Reset";
                SendEmail se = new SendEmail(email, title, message);
                response.sendRedirect("administrator");

            }else{
                //Enable User
                User user = userDao.getUserWithID(email);
                System.out.println("[ServletAdministrator - doPost] ckEnable - " + email);
                if (ckEnable != null){
                    boolean enableSQL = userDao.enableUser(email);
                    if (!enableSQL) {
                        errorMessage += "Account has been not enabled \n";
                    } else {
                        // send confirmation Email
                        String message = "Hi " + user.getFirstname() + " " + user.getLastname() + " ,\n " +
                                "Your account has been enabled.\n\n" +
                                "Have a nice day. \n";
                        System.out.println(message);
                        String title = "[AMT-Project-2018] - enabled account";
                        SendEmail se = new SendEmail(email, title, message);
                        response.sendRedirect("administrator");
                    }
                } else {
                    boolean enableSQL = userDao.disableUser(email);
                    if (!enableSQL) {
                        errorMessage += "Account has been not disabled \n";
                    } else {
                        // send confirmation Email
                        String message = "Hi " + user.getFirstname() + " " + user.getLastname() + " ,\n " +
                                "Your account has been disabled.\n\n" +
                                "Have a nice day. \n";
                        System.out.println(message);
                        String title = "[AMT-Project-2018] - disabled account";
                        SendEmail se = new SendEmail(email, title, message);
                        response.sendRedirect("administrator");
                    }
                }
            }



        }


        response.sendRedirect("administrator");
    }
}
