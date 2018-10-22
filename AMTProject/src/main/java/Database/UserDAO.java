package Database;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO {

    private final static String TABLE_NAME = "users";

    @Resource(lookup = "jdbc/amtProject")
    private DataSource database;

    public Boolean findIfEnableUserExist(String  email, String password) {
        try {
            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT * FROM " + TABLE_NAME +" WHERE email = ? AND password = ? AND enable=1;");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[UserDAO - findIfUserExist]" + (result.getString("email")));
                System.out.println("[UserDAO - findIfUserExist]" + (result.getString("firstname")));
                System.out.println("[UserDAO - findIfUserExist]" + (result.getString("lastname")));

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean checkIfUserExist(String  email) {

        boolean ok = false;

        try {
            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT * FROM " + TABLE_NAME +" WHERE email = ?;");
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[UserDAO - checkIfUserExist]" + (result.getString("email")));
                ok =  true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[UserDAO - checkIfUserExist] return - " + ok);
        return ok;
    }

    public boolean insertUser (String firstname, String lastname, String email, String password, String address,
                               String zip, String country ) {
        boolean ok = true;

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(
                    "INSERT INTO " + TABLE_NAME +
                            "(`firstname`, `lastname`, `email`, `password`, `address`, `zip`, `country`, `admin`, `enable`)" +
                            " VALUES  (? , ? , ?, ?, ?, ?, ?, '0', '1');");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, address);
            ps.setString(6, zip);
            ps.setString(7, country);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[UserDAO - insertUser] return - " + ok);
        return ok;
    }


    public List<String> getAllUsersEmailAddress () {
        ArrayList<String> allEmailAdresses = new ArrayList<String>();

        try {
            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT email FROM " + TABLE_NAME + ";");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                allEmailAdresses.add(result.getString("email"));
                System.out.println("[UserDAO - getAllUsersEmailAddress] - " + result.getString("email") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("[UserDAO - getAllUsersEmailAddress] Return Length - " + allEmailAdresses.size() );
        return allEmailAdresses;

    }

    public boolean updateUserPassword(String email, String password) {
        try {

            PreparedStatement ps = database.getConnection().prepareStatement
                    ("UPDATE " + TABLE_NAME + " SET password = ? WHERE email = ?;");
            ps.setString(1, password);
            ps.setString(2, email);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*


    public User create(User user) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement(
                    "INSERT INTO " +
                            TABLE_NAME +"(email, firstname, lastname, password, is_admin, is_enable, token_validate) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.getIsAdmin());
            statement.setBoolean(6, user.getIsEnable());
            statement.setString(7, user.getTokenValidate());

            //verifie le resultat de l'execution de la requete SQL
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }
            user.setId(findByEmail(user.getEmail()).getId());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean disable(User user) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement(
                    "UPDATE " +
                            TABLE_NAME +" SET is_enable = ?, " +
                            "WHERE id = ?"
            );
            statement.setBoolean(1, false);
            statement.setLong(2, user.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(User user) {
        if (user.getId() != null) {

            String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
            try {
                PreparedStatement prepare = database.getConnection().prepareStatement(sql);
                prepare.setLong(1, user.getId());

                //verifie le resultat de l'execution de la requete SQL
                if (prepare.executeUpdate() == 0) {
                    return false;
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    */
}