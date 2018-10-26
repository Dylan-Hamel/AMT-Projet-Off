package Model;

public class ProjectUser {

    int id;
    String email;
    int projectName;

    public ProjectUser(int id, String email, int projectName) {
        this.id = id;
        this.email = email;
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProjectName() {
        return projectName;
    }

    public void setProjectName(int projectName) {
        this.projectName = projectName;
    }
}
