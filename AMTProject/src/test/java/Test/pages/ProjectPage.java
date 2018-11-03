package Test.pages;

import org.fluentlenium.core.annotation.AjaxElement;
import org.fluentlenium.core.domain.FluentWebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectPage extends AbstractFluentPage {

    private final static String buttonAddApp = "#bAddApp"; // id in the html code
    private final static String nbOfRow = "#nbOfRow";

    @AjaxElement
    FluentWebElement myAjaxElement;

    @Override
    public void isAt() {
        assertThat(title()).isEqualTo("AMT-Project - Project");
    }

    public void changeNumberOFRow(int nbOfRows) {
        // fill(nbOfRow).with(nbOfRows);
    }

    public void clickOnCreateNewApp() {
        click(buttonAddApp);
    }

    public String getUrl() {
        return "/";
    }
}
