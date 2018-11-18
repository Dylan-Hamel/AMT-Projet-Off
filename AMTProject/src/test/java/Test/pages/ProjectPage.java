package Test.pages;

//import org.fluentlenium.core.;
import org.fluentlenium.core.domain.FluentWebElement;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectPage extends AbstractFluentPage {

    private final static String buttonAddApp = "#bAddApp"; // id in the html code
    private final static String nbOfRow = "#nbProjects";
    private final static String dataTable = "#dataTable";
    private final static String nextPage = "#dataTableNext";
    private final static String previousPage = "#dataTablePrevious";

    private final static String pageNum = "#pageNum";

    //FluentWebElement dataTable = find("table");

    @Override
    public void isAt() {
        assertThat(window().title()).isEqualTo("AMT-Project - Project");
    }

    public void changeNumberOfRow(int nbOfRows) {
        await().atMost(1, TimeUnit.NANOSECONDS).untilPage().isLoaded();
        await().atMost(2, TimeUnit.SECONDS).until($(dataTable)).displayed();
        $(nbOfRow).fillSelect().withValue("" + nbOfRows);
    }

    public void clickOnNextPage() {
        await().atMost(1, TimeUnit.NANOSECONDS).untilPage().isLoaded();
        await().atMost(2, TimeUnit.SECONDS).until($(dataTable)).displayed();
        $(nextPage).click();
        await().atMost(2, TimeUnit.SECONDS).until($(dataTable)).displayed();
    }

    public void clickOnPreviousPage() {
        await().atMost(1, TimeUnit.NANOSECONDS).untilPage().isLoaded();
        await().atMost(2, TimeUnit.SECONDS).until($(dataTable)).displayed();
        $(previousPage).click();
        await().atMost(2, TimeUnit.SECONDS).until($(dataTable)).displayed();
    }

    public void clickOnCreateNewApp() {
        $(buttonAddApp).click();
    }

    public int getPageNumber() {
        return Integer.parseInt($(pageNum).get(0).value());
    }

    public int getNumberOfRows() {
        return Integer.parseInt($(nbOfRow).get(0).value());
    }

    public String getUrl() {
        return "/project";
    }
}
