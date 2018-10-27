package Database;


import Model.Project;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ProjectDAO {

    private final static String TABLE_NAME = "t_users_projects";

    @Resource(lookup = "jdbc/amtProject")
    private DataSource database;

    public ArrayList<Project> getAllProjectByUser(String user) {
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME + " INNER JOIN projects " +
                    "ON projects.name = t_users_projects.project " +
                    "WHERE t_users_projects.email = ?;";
            PreparedStatement prepare = database.getConnection().prepareStatement(sql);
            prepare.setString(1, user);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {

                System.out.println("[ProjectDAO - findByUser] name - " + result.getString("name" ));

                Project project = new Project(result.getString("name"),
                        result.getString("description"),
                        result.getString("api_key"),
                        result.getString("api_secret"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public boolean insertProjet (String name, String description, String api_key, String api_secret) {
        boolean ok = true;

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(
                    "INSERT INTO projects " +
                            "(`name`, `description`, `api_key`, `api_secret`)" +
                            " VALUES  (? , ? , ?, ?);");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, api_key);
            ps.setString(4, api_secret);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[ProjectDAO - insertProjet] return - " + ok);
        return ok;

    }

    public boolean insertProjetUser (String email, String project) {
        boolean ok = true;

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(
                    "INSERT INTO " + TABLE_NAME +
                            "(`email`, `project`)" +
                            " VALUES  (? , ? );");
            ps.setString(1, email);
            ps.setString(2, project);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Updates failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[ProjectDAO - insertProjetUser] return - " + ok);
        return ok;

    }

    public Boolean checkIfProjectExist(String name) {
        boolean ok = false;

        try {
            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT * FROM projects WHERE name = ?;");
            ps.setString(1, name);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[ProjectDAO - checkIfProjecExist]" + (result.getString("email")));
                ok =  true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ok =  true;
        }
        System.out.println("[ProjectDAO - checkIfProjecExist] return - " + ok);
        return ok;
    }



}