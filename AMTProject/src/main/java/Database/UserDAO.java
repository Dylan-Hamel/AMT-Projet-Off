package Database;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UserDAO {

    private final static String TABLE_NAME = "users";

    @Resource(lookup = "jdbc/amtProject")
    private DataSource database;

    public Boolean findIfEnableUserExist(String  email, String password) {
        try {
            PreparedStatement prepare = database.getConnection()
                    .prepareStatement("SELECT * FROM " + TABLE_NAME +" WHERE email = ? AND password = ? AND enable=1;");
            prepare.setString(1, email);
            prepare.setString(2, password);
            ResultSet result = prepare.executeQuery();
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

    public void insertUser (String firstname, String lastname, String email, String password, String address,
                               String zip, String country ) {
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

            if (ps.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public boolean update(User user) {
        try {
            String sql = "UPDATE " +
                    TABLE_NAME +
                    " SET email = ?, " +
                    "firstname = ?, " +
                    "lastname = ?, " +
                    "password = ?, " +
                    "is_admin = ?, " +
                    "is_enable = ?, " +
                    "token_validate = ? " +
                    "WHERE id = ?";
            PreparedStatement statement = database.getConnection().prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLastname());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.getIsAdmin());
            statement.setBoolean(6, user.getIsEnable());
            statement.setString(7, user.getTokenValidate());
            statement.setLong(8, user.getId());

            //verifie le resultat de l'execution de la requete SQL
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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