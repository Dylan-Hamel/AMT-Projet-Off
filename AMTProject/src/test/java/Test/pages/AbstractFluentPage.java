package Test.pages;

import org.fluentlenium.core.FluentPage;


/**
 * Most pages served by the application have the same structure: they have the
 * same header (with a navigation menu) and the same footer. These elements, and
 * the WebDriver UI locators, are capture in this abstract class. This makes it
 * possible to issue "clicks" on the navigation menu via the API from any page,
 * without code duplication.
 *
 * @author Olivier Liechti, Yannis Ansermoz
 */
public abstract class AbstractFluentPage extends FluentPage {

    /**
     * These locators are used to find elements in the web page. In the code, 'id'
     * is the HTML id attribute that is defined in the page markup. This shows
     * that when you write automated tests with Selenium, you have to prepare your
     * HTML code so that important elements have an id (which can then be
     * referenced in the test). Note that there are other types of locators. See:
     * http://www.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
     */
    private final static String menuExamples = "#menu"; // this is the HTML id of the menu
    private final static String menuItemHome = "#home"; // this is the HTML id of the menu
    private final static String menuItemProject = "#project";
    private final static String menuItemAdministrator = "#administrator";
    private final static String menuItemLogout = "#logout";
    private final String baseUrl = "http://192.168.99.100:9090/AMT-Projet";

    /**
     * This method illustrates two aspects of the Page Object pattern.
     *
     * Firstly, notice that we expose a user intent (he wants to go to the Beers
     * List page by selecting the proper item in the navigation menu), but we hide
     * the details (when calling this method, you don't need to know about HTML
     * IDs and other implementation details that could change). It makes the tests
     * easier to read and more robust (if an HTML ID changes, you only need to
     * change the Page and not all the tests that use it).
     */
    public void goToHomePageViaMenu() {
        //$(menuExamples).click();
        $(menuItemHome).click();
    }

    public void goToProjectPageViaMenu() {
        //$(menuExamples).click();
        $(menuItemProject).click();
    }

    public void goToAdministratorPageViaMenu() {
        //$(menuExamples).click();
        $(menuItemAdministrator).click();
    }

    public void goToLogoutPageViaMenu() {
        //$(menuExamples).click();
        $(menuItemLogout).click();
    }

}
