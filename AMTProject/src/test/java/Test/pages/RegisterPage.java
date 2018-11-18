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
    private final static String registerForm = "#registerForm";

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("AMT-Project - Register");
    }

    public void typeFirstName(String firstName) {
        $(inputFirstName).fill().with(firstName);
    }

    public void typeLastName(String lastName) {
        $(inputLastName).fill().with(lastName);
    }

    public void typeEmailAddress(String email) {
        $(inputEmail).fill().with(email);
    }

    public void typeAddress(String address) {
        $(inputAddress).fill().with(address);
    }

    public void typeZip(String zip) {
        $(inputZip).fill().with(zip);
    }

    public void typeCountry(String country) {
        $(inputCountry).fill().with(country);
    }

    public void typePassword(String password) {
        $(inputPassword).fill().with(password);
    }

    public void clickRegister() {
        $(buttonRegister).submit();
    }

    public void fillAndRegister(String... paramsOrdered){
        $("input").fill().with(paramsOrdered);
        //$(registerForm).submit();
        $(buttonRegister).click();
    }

    public String getUrl() {
        return "/register";
    }
}
