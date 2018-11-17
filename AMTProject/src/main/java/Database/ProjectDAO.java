package Database;


import Model.Project;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.EJB;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Stateless
public class ProjectDAO implements ProjectInterface {

    private final static String TABLE_NAME_JOIN = "t_users_projects";
    private final static String TABLE_NAME = "projects";
    private final static String EMAIL_ASSIGN = "backup@backup.backup";


    @Resource(lookup = "java:/jdbc/amtProject")
    private DataSource database;


    /*
     *
     */
    @Override
    public ArrayList<Project> getAllProjectByUser(String user) {
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_JOIN + " INNER JOIN projects " +
                    "ON " + TABLE_NAME + ".name = " +  TABLE_NAME_JOIN + ".project " +
                    "WHERE " + TABLE_NAME_JOIN + ".email = ?;";
            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ps.setString(1, user);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                System.out.println("[ProjectDAO - findByUser] name - " + result.getString("name" ));

                Project project = new Project(result.getString("name"),
                        result.getString("description"),
                        result.getString("api_key"),
                        result.getString("api_secret"));
                projects.add(project);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }


    /*
     *
     */
    @Override
    public ArrayList<Project> getProjectByUser(String user, int nbOfRecords, int beginRecord) {
        ArrayList<Project> projects = new ArrayList<Project>(nbOfRecords);
        try {
            String sql = "SELECT * FROM " + TABLE_NAME_JOIN + " INNER JOIN projects " +
                    "ON " + TABLE_NAME + ".name = " +  TABLE_NAME_JOIN + ".project " +
                    "WHERE " + TABLE_NAME_JOIN + ".email = ? LIMIT ?, ?;";
            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, beginRecord);
            ps.setInt(3, nbOfRecords);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                System.out.println("[ProjectDAO - findByUser] name - " + result.getString("name" ));

                Project project = new Project(result.getString("name"),
                        result.getString("description"),
                        result.getString("api_key"),
                        result.getString("api_secret"));
                projects.add(project);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    /*
     *
     */
    @Override
    public int countProjectByUser(String user) {
        int nbProjects = 0;
        try {
            String sql = "SELECT COUNT(*) AS nbProjets FROM " + TABLE_NAME_JOIN + " INNER JOIN projects " +
                    "ON " + TABLE_NAME + ".name = " +  TABLE_NAME_JOIN + ".project " +
                    "WHERE " + TABLE_NAME_JOIN + ".email = ?;";
            PreparedStatement ps = database.getConnection().prepareStatement(sql);
            ps.setString(1, user);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                nbProjects = Integer.parseInt(result.getString("nbProjets"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbProjects;
    }

    /*
     * Insert into Projects table
     */
    @Override
    public boolean insertProjet(String name, String description, String api_key, String api_secret) {
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
                ps.close();
                throw new SQLException("Updates failed");
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[ProjectDAO - insertProjet] return - " + ok);
        return ok;

    }

    /*
     * Insert into Projects t_users_projects
     */
    @Override
    public boolean insertProjetUser(String email, String project) {
        boolean ok = true;

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(
                    "INSERT INTO " + TABLE_NAME_JOIN +
                            "(`email`, `project`)" +
                            " VALUES  (? , ? );");
            ps.setString(1, email);
            ps.setString(2, project);

            // Check SQL Execution
            if (ps.executeUpdate() == 0) {
                ps.close();
                throw new SQLException("Updates failed");
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[ProjectDAO - insertProjetUser] return - " + ok);
        return ok;

    }

    /*

     */
    @Override
    public boolean checkIfProjectExist(String name) {
        boolean ok = false;

        try {
            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE name = ?;");
            ps.setString(1, name);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("[ProjectDAO - checkIfProjecExist]" + (result.getString("email")));
                ok =  true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ok =  true;
        }
        System.out.println("[ProjectDAO - checkIfProjecExist] return - " + ok);
        return ok;
    }


    /*
     * This function will delete a project from t_user_project table and projects table
     * t_user_project is a table which join users and projects
     */
    @Override
    public boolean deleteProjectFromJoinTableAndProject(String name) {
        boolean ok = false;
        try {
            PreparedStatement ps = database.getConnection()
                    .prepareStatement("DELETE FROM " + TABLE_NAME_JOIN + " WHERE project = ?;");
            ps.setString(1, name);
            if (ps.executeUpdate() == 0) {
                ok = false;
            }

            PreparedStatement psProject = database.getConnection()
                    .prepareStatement("DELETE FROM " + TABLE_NAME + "  WHERE name = ?");
            psProject.setString(1, name);


            if (psProject.executeUpdate() == 0) {
                ok = false;
            } else {
                ok = true;
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean reassignProjectOfUser(String email){
        boolean ok = true;

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(
                    "UPDATE " + TABLE_NAME_JOIN +
                            " SET email = '" + EMAIL_ASSIGN +
                            "' WHERE email = ?;");
            ps.setString(1, email);

            // throw new RuntimeException("Reassign failed - rollback test");

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ok = false;
        }
        System.out.println("[ProjectDAO - reassignProjectOfUser] return - " + ok);
        return ok;
    }


    /*

     */
    @Override
    public ArrayList<String> getAllAPIKey() {
        ArrayList<String> allAPIKey = new ArrayList<String>();
        try {

            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT api_key FROM " + TABLE_NAME + ";" );
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                System.out.println("[ProjectDAO - getAllAPIKey] - " + result.getString("api_key" ));
                allAPIKey.add(result.getString("api_key" ));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAPIKey;
    }


    /*

     */
    @Override
    public ArrayList<String> getAllAPISecret() {
        ArrayList<String> allAPISecret = new ArrayList<String>();
        try {

            PreparedStatement ps = database.getConnection()
                    .prepareStatement("SELECT api_secret FROM " + TABLE_NAME + ";" );
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                System.out.println("[ProjectDAO - getAllAPIKey] - " + result.getString("api_secret" ));
                allAPISecret.add(result.getString("api_secret" ));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAPISecret;
    }


    @Override
    public boolean updateProjectDescription (String name, String description) {
        boolean ok = false;

        System.out.println("[ProjectDAO - updateProjectDescription] name" + name);
        System.out.println("[ProjectDAO - updateProjectDescription] description" + description);

        try {

            PreparedStatement ps = database.getConnection()
                    .prepareStatement("UPDATE " + TABLE_NAME + " SET description=? WHERE name=?;");
            ps.setString(1, description);
            ps.setString(2, name);


            if (ps.executeUpdate() == 0) {
                ps.close();
                throw new SQLException("Updates failed");
            }
            ps.close();
            ok = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

}