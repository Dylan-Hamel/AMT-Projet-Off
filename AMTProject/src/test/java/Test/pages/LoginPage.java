package Test.pages;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class LoginPage extends AbstractFluentPage {
    private final static String inputEmail = "#email"; // id in the html code
    private final static String inputPassword = "#password"; // id in the html code
    private final static String buttonSignin = "#bSignIn"; // id in the html code
    private final static String formSignIn = "#fSignIn";

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("AMT-Project - Login");
    }

    public void typeEmailAddress(String email) {
        await().atMost(1, TimeUnit.NANOSECONDS).untilPage().isLoaded();
        // await().atMost(2, TimeUnit.SECONDS).until($(inputEmail)).displayed();
        $(inputEmail).fill().with(email);

    }

    public void typePassword(String password) {
        $(inputPassword).fill().with(password);
    }

    public void clickSignin() {
        // $(formSignIn).submit();
        $(buttonSignin).click();
    }

    public void fillAndSignIn(String... paramsOrdered){
        $("input").fill().with(paramsOrdered);
        // $(formSignIn).submit();
        $(buttonSignin).click();
    }

    public String getErrorMsg(){
        return $("error-msg").get(0).text();
        //return $("error-msg").get(0).value();
    }

    public String getUrl() {
        return getBaseUrl() + "/login";
    }
}
