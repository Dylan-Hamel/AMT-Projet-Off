package Database;

import Model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.EJB;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
public class UserDAO implements UserInterface {

    @EJB(beanName ="ProjectDAO")
    ProjectInterface projectDAO;

    private final static String TABLE_NAME = "users";

    @Resource(lookup = "java:/jdbc/amtProject")
    private DataSource database;

    /*
     *
     */
    @Override
    public boolean findIfUserExist(String  email, String password) {
        System.out.println("[UserDAO - findIfEnableUserExist] - Start");
        boolean ok = false;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME +" WHERE email = ? AND password = ? ;");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[UserDAO - findIfUserExist]" + (result.getString("email")));
                System.out.println("[UserDAO - findIfUserExist]" + (result.getString("firstname")));
                System.out.println("[UserDAO - findIfUserExist]" + (result.getString("lastname")));

                ok = true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    /*
     *
     */
    @Override
    public boolean checkIfUserExist(String  email) {
        boolean ok = false;

        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME +" WHERE email = ?;");
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[UserDAO - checkIfUserExist]" + (result.getString("email")));
                ok =  true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[UserDAO - checkIfUserExist] return - " + ok);
        return ok;
    }

    /*
     *
     */
    @Override
    public boolean insertUser (String firstname, String lastname, String email, String password, String address,
                               String zip, String country ) {
        boolean ok = true;

        try {
            Connection conn = database.getConnection();
			PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO " + TABLE_NAME +
                            "(`firstname`, `lastname`, `email`, `password`, `address`, `zip`, `country`, `admin`, `enable`, `reset`)" +
                            " VALUES  (? , ? , ?, ?, ?, ?, ?, '0', '1', '0');");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setString(6, zip);
            ps.setString(7, country);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[UserDAO - insertUser] return - " + ok);
        return ok;
    }


    /*
     *
     */
    @Override
    public List<String> getAllUsersEmailAddress () {
        ArrayList<String> allEmailAdresses = new ArrayList<String>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT email FROM " + TABLE_NAME + ";");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                allEmailAdresses.add(result.getString("email"));
                System.out.println("[UserDAO - getAllUsersEmailAddress] - " + result.getString("email") );
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[UserDAO - getAllUsersEmailAddress] Return Length - " + allEmailAdresses.size() );
        return allEmailAdresses;

    }

    /*
     *
     */
    @Override
    public boolean updateUserPassword(String email, String password) {
        boolean ok = false;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET password = ? WHERE email = ?;");
            ps.setString(1, password);
            ps.setString(2, email);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            } else {
                ps.close();
                conn.close();
                ok = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /*
     *
     */
    @Override
    public User getUserWithID(String email) {
        User user = new User();
        try {
            Connection conn = database.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                user.setFirstname(result.getString("firstname"));
                user.setLastname(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setAddress(result.getString("address"));
                user.setZip(result.getString("zip"));
                user.setCountry(result.getString("country"));
                user.setAdmin(result.getBoolean("admin"));
                user.setEnable(result.getBoolean("enable"));
                user.setReset(result.getBoolean("reset"));
                System.out.println("[UserDAO - getUserWithID] - " + user.getEmail());
                ps.close();
                conn.close();
                return user;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     *
     */
    @Override
    public boolean update(String firstname, String lastname, String email, String password, String address, String zip, String country) {
        boolean ok = false;
        try {
            String sql = "UPDATE " +
                    TABLE_NAME +
                    " SET firstname = ?, " +
                    "lastname = ?, " +
                    "password = ?, " +
                    "address = ?, " +
                    "zip = ?, " +
                    "country = ? " +
                    "WHERE email = ?";
            Connection conn = database.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, password);
            ps.setString(4, address);
            ps.setString(5, zip);
            ps.setString(6, country);
            ps.setString(7, email);

            // Check Result
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            } else {
                ok = true;
                ps.close();
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /*
     *
     */
    @Override
    public List<User> getAllUsersEmailAndStatus () {

        System.out.println("[UserDAO - getAllUsersEmailAndStatus] - Start");

        List<User> usersEmailAndStatus = new LinkedList<User>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT email, enable  FROM " + TABLE_NAME + ";");
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                String email = result.getString("email");
                boolean enable = result.getBoolean("enable");

                System.out.println("[UserDAO - getAllUsersEmailAndStatus] email - " + email);
                System.out.println("[UserDAO - getAllUsersEmailAndStatus] enable - " + enable);

                User user = new User(email, enable);
                usersEmailAndStatus.add(user);
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[UserDAO - getAllUsersEmailAndStatus] return length - " + usersEmailAndStatus.size());
        return usersEmailAndStatus;
    }

    /*
     *
     */
    @Override
    public boolean checkIfUserHaveResetedPassword (String email) {
        boolean ok = false;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT reset FROM " + TABLE_NAME +" WHERE email = ?;");
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[UserDAO - checkIfUserHaveResetedPassword]" + (result.getBoolean("reset")));
                ok =  true;
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[UserDAO - checkIfUserHaveResetedPassword] return - " + ok);
        return ok;
    }

    /*
     * This function is used when a user has set his new password after a reset
     */
    @Override
    public boolean setUserResetTo0 (String email) {
        boolean ok = true;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET reset = 0 WHERE email = ?;");
            ps.setString(1, email);
            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            }
            ps.close();
            conn.close();
            ok =  true;
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /*
     * This function is used when a user "Forgot Username / Password?" feature
     * Set reset to 1, so that user will change his password
     */
    @Override
    public boolean setUserResetTo1 (String email) {
        boolean ok = true;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET reset = 1 WHERE email = ?;");
            ps.setString(1, email);
            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /*
     *
     */
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean deleteUser (String email) {
        boolean ok = false;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE email = ?;");
            ps.setString(1, email);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Delete Failed");
            }

            projectDAO.reassignProjectOfUser(email);

            ps.close();
            conn.close();
            ok = true;
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /*
     *
     */
    public boolean enableUser (String email) {

        System.out.println("[UserDAO - enableUser] - Start");
        System.out.println("[UserDAO - enableUser] - " + email);

        boolean ok = false;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET enable = 1 WHERE email = ?;");
            ps.setString(1, email);
            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            }
            ps.close();
            conn.close();
            ok = true;
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    /*
     *
     */
    public boolean disableUser (String email) {
        boolean ok = false;
        try {
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET enable = 0 WHERE email = ?;");
            ps.setString(1, email);
            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                conn.close();
                throw new SQLException("Updates failed");
            }
            ps.close();
            conn.close();
            ok = true;
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }


}