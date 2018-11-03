package Test.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends AbstractFluentPage {
    private final static String inputEmail = "#email"; // id in the html code
    private final static String inputPassword = "#password"; // id in the html code
    private final static String buttonSignin = "#bSignIn"; // id in the html code

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("AMT-Project - Login");
    }

    public void typeEmailAddress(String email) {
        $(inputEmail).fill().with(email);
    }

    public void typePassword(String password) {
        $(inputPassword).fill().with(password);
    }

    public void clickSignin() {
        $(buttonSignin).submit();
    }

    public void fillAndSignIn(String... paramsOrdered){
        $("input").fill().with(paramsOrdered);
        $(buttonSignin).submit();
    }

    public String getUrl() {
        return "/login";
    }
}
