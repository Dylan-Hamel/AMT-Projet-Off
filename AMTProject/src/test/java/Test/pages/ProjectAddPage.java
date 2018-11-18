package Test.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectAddPage extends AbstractFluentPage {

    private final static String inputName = "#name"; // id in the html code
    private final static String inputDescription = "#description"; // id in the html code
    private final static String buttonAddApp = "#bAddApp";
    private final static String formAddApp = "#fAddApp";

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("AMT-Project - Project Add");
    }

    public void typeName(String name) {
        $(inputName).fill().with(name);
    }

    public void typeDescription(String description) {
        $(inputDescription).fill().with(description);
    }

    public void clickAddApp() {
        //$(formAddApp).submit();
        $(buttonAddApp).click();
    }

    public String getUrl() {
        return "/projectadd";
    }
}
