package Model;


public class Project {

    private String name;
    private String description;
    private String api_key;
    private String api_secret;

    public Project(String name, String description, String api_key, String api_secret) {
        this.name = name;
        this.description = description;
        this.api_key = api_key;
        this.api_secret = api_secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }
}
