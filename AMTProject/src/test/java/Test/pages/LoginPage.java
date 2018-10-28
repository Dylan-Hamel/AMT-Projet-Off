package Test.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends AbstractFluentPage {
    private final static String inputEmail = "#inputEmail"; // id in the html code
    private final static String inputPassword = "#inputPassword"; // id in the html code
    private final static String buttonSignin = "#bSignIn"; // id in the html code

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("Login Page");
    }

    public void typeEmailAddress(String email) {
        fill(inputEmail).with(email);
    }

    public void typePassword(String password) {
        fill(inputPassword).with(password);
    }

    public void clickSignin() {
        click(buttonSignin);
    }

    public String getUrl() {
        return "/";
    }
}
