package Test.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterPage extends AbstractFluentPage {

    private final static String inputFirstName = "#firstName"; // id in the html code
    private final static String inputLastName = "#lastName"; // id in the html code
    private final static String inputEmail = "#email"; // id in the html code
    private final static String inputPassword = "#password"; // id in the html code
    private final static String inputAddress = "#address"; // id in the html code
    private final static String inputZip = "#zip"; // id in the html code
    private final static String inputCountry = "#country"; // id in the html code
    private final static String buttonRegister = "#bRegister"; // id in the html code

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("AMT-Project - Register");
    }

    public void typeFirstName(String firstName) {
        fill(inputFirstName).with(firstName);
    }

    public void typeLastName(String lastName) {
        fill(inputLastName).with(lastName);
    }

    public void typeEmailAddress(String email) {
        fill(inputEmail).with(email);
    }

    public void typeAddress(String address) {
        fill(inputAddress).with(address);
    }

    public void typeZip(String zip) {
        fill(inputZip).with(zip);
    }

    public void typeCountry(String country) {
        fill(inputCountry).with(country);
    }

    public void typePassword(String password) {
        fill(inputPassword).with(password);
    }

    public void clickRegister() {
        click(buttonRegister);
    }

    public String getUrl() {
        return "/";
    }
}
