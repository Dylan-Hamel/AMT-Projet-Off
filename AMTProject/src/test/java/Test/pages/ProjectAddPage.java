package Test.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectAddPage extends AbstractFluentPage {

    private final static String inputName = "#name"; // id in the html code
    private final static String inputDescription = "#description"; // id in the html code
    private final static String buttonAddApp = "#bAddApp"; // id in the html code

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("AMT-Project - Project Add");
    }

    public void typeName(String name) {
        fill(inputName).with(name);
    }

    public void typeDescription(String description) {
        fill(inputDescription).with(description);
    }

    public void clickAddApp() {
        click(buttonAddApp);
    }

    public String getUrl() {
        return "/";
    }
}
