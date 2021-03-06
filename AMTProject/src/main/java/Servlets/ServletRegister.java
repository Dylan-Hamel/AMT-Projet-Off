package Servlets;

import Database.UserInterface;
import Utils.SendEmail;
import Utils.SendEmailInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;

public class ServletRegister extends javax.servlet.http.HttpServlet {

    @EJB(beanName ="UserDAO")
    UserInterface userDao;

    @EJB(beanName ="SendEmail")
    SendEmailInterface se;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletRegister - doPost]");

        String errorMessage = "";
        boolean registerFormOK = true;


        // Retrieve value from register form
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");


        System.out.println("[ServletRegister - doPost] firstname - " + firstname);
        System.out.println("[ServletRegister - doPost] lastname - " + lastname);
        System.out.println("[ServletRegister - doPost] email - " + email);
        System.out.println("[ServletRegister - doPost] password - " + password);
        System.out.println("[ServletRegister - doPost] address - " + address);
        System.out.println("[ServletRegister - doPost] zip - " + zip);
        System.out.println("[ServletRegister - doPost] country - " + country);


        // Check empty fields
        if (firstname == null || firstname.isEmpty())  {
            firstname = "";
            errorMessage += "Error in Firstname ";
            registerFormOK = false;
        }

        if (lastname == null || lastname.isEmpty())  {
            lastname = "";
            errorMessage += "Error in Lastname";
            registerFormOK = false;
        }

        if (email == null || email.isEmpty())  {
            email = "";
            errorMessage += "Error in Email";
            registerFormOK = false;
        }

        if (password == null || password.isEmpty())  {
            password = "";
            errorMessage += "Error in Password";
            registerFormOK = false;
        }

        if (address == null || address.isEmpty())  {
            address = "";
            errorMessage += "Error in Address";
            registerFormOK = false;
        }

        if (zip == null || zip.isEmpty())  {
            zip = "";
            errorMessage += "Error in Zip";
            registerFormOK = false;
        }

        if (country == null || country.isEmpty())  {
            country = "";
            errorMessage += "Error in Country";
            registerFormOK = false;
        }

        System.out.println("[ServletRegister - doPost] registerFormOK - " + registerFormOK);

        if (!registerFormOK) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("address", address);
            request.setAttribute("zip", zip);
            request.setAttribute("country", country);

            request.getRequestDispatcher("/WEB-INF/pages/register/register.jsp").forward(request, response);
        } else {

            // 1.Check if email is already exist
            // 2.Insert une DB and if there is an issue error during insert
            // 3.Else return to login page and user has been added
            if (userDao.checkIfUserExist(email)) {
                System.out.println("[ServletRegister - doPost] - 1");
                errorMessage = "User already exists";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pages/register/register.jsp").forward(request, response);
            } else if (!userDao.insertUser(firstname, lastname, email, password, address, zip, country)) {
                System.out.println("[ServletRegister - doPost] - 2");
                errorMessage = "Error during insert";

                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pages/register/register.jsp").forward(request, response);
            } else {
                System.out.println("[ServletRegister - doPost] - 3");

                // send confirmation Email
                String message = "Hi " + firstname + " " + lastname + " ,\n " +
                        "Your account has been created \n" +
                        "Below your account information : \n" +
                        "Firstname : " + firstname + "\n" +
                        "Lastname  : " + lastname + "\n" +
                        "Password  : " + password + "\n" +
                        "Address   : " + address + "\n" +
                        "ZIP       : " + zip + "\n" +
                        "country   : " + country + "\n";

                System.out.println(message);

                String title = "[AMT-Project-2018] - New Account";

                se.sendEmail(email, title, message);

                response.sendRedirect("login");
            }
        }
    }


    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletRegister - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/register/register.jsp").forward(request, response);
    }

}

