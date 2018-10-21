package Servlets;

import Database.UserDAO;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;

public class ServletRegister extends javax.servlet.http.HttpServlet {

    @EJB
    private UserDAO userDao;

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


            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {

            userDao.insertUser(firstname,lastname,email,password,address,zip,country);

            response.sendRedirect("login");
        }





    }


    @Override
    protected void doGet (javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[ServletRegister - doGet]");

        request.getRequestDispatcher("/WEB-INF/pages/register/register.jsp").forward(request, response);
    }

}

