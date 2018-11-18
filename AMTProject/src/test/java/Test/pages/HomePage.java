package Test.pages;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class is used to test the "Home" page in the Project app. Notice that in
 * the constructor, we check if we are on the correct page by checking the HTML
 * title of the page. This is used to detect navigation issues (for example, you
 * expect to arrive on the projects page, but the title of the actual page is
 * "Login Page" because of some error.
 *
 * @author Yannis Ansermoz
 */
public class HomePage extends AbstractFluentPage {

    String[] homePageFields = {"#firstname", "#lastname", "#email", "#address", "#zip", "#country"};

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("AMT-Project - Home");
    }

    public void checkLoggedInUserInfos(String... paramsOrdered){
        int i = 0;
        for (String param : paramsOrdered) {
            assertThat($(homePageFields[i]).values().get(0)).isEqualTo(param);
            i++;
        }
    }

    public String getUrl() {
        return "/home";
    }

}
