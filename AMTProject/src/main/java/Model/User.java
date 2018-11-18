package Model;

public class User {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String address;
    private String zip;
    private String country;
    private boolean isAdmin;
    private boolean isEnable;
    private boolean reset;

    // CONSTRUCTOR


    public User(String email) {

    }

    public User () {

    }

    public User(String email, boolean enable) {
        this.email = email;
        this.isEnable = enable;
    }

    public User(String email, String firstname, String lastname, String password,
                String address, String zip, String country, boolean isAdmin, boolean isEnable) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.address = address;
        this.zip = zip;
        this.country = country;
        this.isAdmin = isAdmin;
        this.isEnable = isEnable;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public User(String email, String firstname, String lastname, String password, String address,
                String zip, String country, boolean isAdmin, boolean isEnable, boolean reset) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.address = address;
        this.zip = zip;
        this.country = country;
        this.isAdmin = isAdmin;
        this.isEnable = isEnable;
        this.reset = reset;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

}
