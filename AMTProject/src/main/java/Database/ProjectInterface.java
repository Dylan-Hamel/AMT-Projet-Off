package Database;

import Model.Project;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface ProjectInterface {
    /*
     *
     */
    ArrayList<Project> getAllProjectByUser(String user);

    /*
     *
     */
    ArrayList<Project> getProjectByUser(String user, int nbOfRecords, int beginRecord);

    /*
     *
     */
    int countProjectByUser(String user);

    /*
     * Insert into Projects table
     */
    boolean insertProjet(String name, String description, String api_key, String api_secret);

    /*
     * Insert into Projects t_users_projects
     */
    boolean insertProjetUser(String email, String project);

    /*

     */
    Boolean checkIfProjectExist(String name);

    /*
     * This function will delete a project from t_user_project table and projects table
     * t_user_project is a table which join users and projects
     */
    boolean deleteProjectFromJoinTableAndProject(String name);

    /*

     */
    ArrayList<String> getAllAPIKey();

    /*

     */
    ArrayList<String> getAllAPISecret();

    /*

     */
    boolean updateProjectDescription (String name, String description);
}
