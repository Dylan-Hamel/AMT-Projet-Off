package Test;

import Database.UserDAO;
import Test.pages.HomePage;
import Test.pages.LoginPage;
import Test.pages.ProjectAddPage;
import Test.pages.ProjectPage;
import Test.pages.RegisterPage;
//import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.Test;
import org.fluentlenium.core.annotation.Page;

import javax.ejb.EJB;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;


public class AMTProjetFluentTest extends FluentTest {
    /*
    `TESTING_FUNCTIONAL.md`
1 - developer creates an account
2 - developer logs in
3 - developer creates 25 pages
4 - developer browses the list of applications (3 pages of 10, 10 and 5 applications)
5 - developer logs out
6 - developer tries to go back to the list of applications and is redirected to login page

The tests should include assertions, so that changing the code (e.g. introducing a bug) breaks the code.

The report should describe and document a concrete example (with screenshots).
     */

    private final String baseUrl = "http://192.168.99.100:9090/AMT-Projet";

    private final String existingUserEmail = "test@test.test";
    private final String existingUserPWD = "testPWD";

    private final String newUserFirstName = "testFN";
    private final String newUserLastName = "testLN";
    private final String newUserEmail = "test2@test.test";
    private final String newUserAddress = "testAdd";
    private final String newUserZip = "testZIP";
    private final String newUserCountry = "testCountry";
    private final String newUserPWD = "testPWD";

    private final String newAppName = "testAppN";
    private final String newAppDescription = "testAppDesc";

    private final String loginErrorMsg = "Error in EMAIL/PASSWORD";
    private final String accountDisabledMsg = "Account Disabled";

    @EJB
    private UserDAO userDao;

    @Page
    private LoginPage loginPage;

    @Page
    private HomePage homePage;

    @Page
    private ProjectAddPage projectAddPage;

    @Page
    private ProjectPage projectPage;

    @Page
    private RegisterPage registerPage;

    public AMTProjetFluentTest() {
        getConfiguration().setScreenshotMode(TriggerMode.AUTOMATIC_ON_FAIL);
        getConfiguration().setHtmlDumpMode(TriggerMode.AUTOMATIC_ON_FAIL);
        getConfiguration().setWebDriver("firefox");
        setBaseUrl(baseUrl);
    }


    @Test
    public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
        //goTo(baseUrl + "/login");
        loginPage.go();
        loginPage.isAt();
        /*
        loginPage.typeEmailAddress("not a valid email");
        loginPage.typePassword("any password");
        loginPage.clickSignin();
        */
        loginPage.fillAndSignIn("not a valid email", "any password");
        loginPage.isAt();
        assert(loginPage.getErrorMsg().equals(loginErrorMsg));
    }

    // 1 - developer creates an account
    @Test
    public void itShouldBePossibleToCreateANewUser() {
        // first remove existing user
        //userDao.deleteUser(newUserEmail);

        //goTo(baseUrl + registerPage);
        registerPage.go();
        registerPage.isAt();
        /*
        registerPage.typeFirstName(newUserFirstName);
        registerPage.typeLastName(newUserLastName);
        registerPage.typeEmailAddress(newUserEmail);
        registerPage.typeAddress(newUserAddress);
        registerPage.typeZip(newUserZip);
        registerPage.typeCountry(newUserCountry);
        registerPage.typePassword(newUserPWD);
        registerPage.clickRegister();
        */
        registerPage.fillAndRegister(newUserFirstName, newUserLastName, newUserEmail, newUserPWD, newUserAddress, newUserZip, newUserCountry);
        loginPage.isAt(); // we should be redirected toward the login target after register
        loginPage.fillAndSignIn(newUserEmail, newUserPWD);
        homePage.isAt();
        homePage.checkLoggedInUserInfos(newUserFirstName, newUserLastName, newUserEmail, newUserAddress, newUserZip, newUserCountry);
    }


    // 2 - developer logs in
    @Test
    public void successfulSigninShouldBringUserToHomePage() {
        //goTo(baseUrl + loginPage);
        loginPage.go();
        loginPage.isAt();

        //loginPage.typeEmailAddress("yannis@me.me");
        //loginPage.typePassword("123123");
        //loginPage.clickSignin();

        loginPage.fillAndSignIn(existingUserEmail, existingUserPWD);
        homePage.isAt();
    }

    // 3 - developer creates 25 pages
    @Test
    public void itShouldBePossibleToCreateANewAppAfterSignin() {
        //goTo(baseUrl + projectPage);
        //projectPage.go();
        loginPage.isAt(); // we have not logged in, so we should be redirected
        /*
        loginPage.typeEmailAddress(newUserEmail);
        loginPage.typePassword(newUserPWD);
        loginPage.clickSignin();
        */
        loginPage.fillAndSignIn(newUserEmail, newUserPWD);
        projectPage.go();
        projectPage.isAt(); // we should be redirected toward the original target after signin
        projectPage.clickOnCreateNewApp();
        projectAddPage.isAt();
        for(int i = 1; i <= 25; i++) {
            projectAddPage.typeName(newAppName + i);
            projectAddPage.typeDescription(newAppDescription + i);
            projectAddPage.clickAddApp();
        }

        /*
        takeScreenShot();
        takeHtmlDump();
        */
    }

    // 4 - developer browses the list of applications (3 pages of 10, 10 and 5 applications)
    @Test
    public void itShouldBePossibleToGetProjectsListAfterSignIn() {
        //goTo(baseUrl + projectPage);
        //projectPage.go();
        loginPage.isAt(); // we have not logged in, so we should be redirected
        /*
        loginPage.typeEmailAddress(newUserEmail);
        loginPage.typePassword(newUserPWD);
        loginPage.clickSignin();
        */
        loginPage.fillAndSignIn(newUserEmail, newUserPWD);
        projectPage.go();
        projectPage.isAt();
        projectPage.clickOnNextPage();
        projectPage.clickOnNextPage();
        projectPage.changeNumberOfRow(5);
    }


    // 5 - developer logs out
    // 6 - developer tries to go back to the list of applications and is redirected to login page
    @Test
    public void itShouldBeImpossibleToGetProjectsListAfterSignOut() {
        //goTo(baseUrl + projectPage);
        projectPage.go();
        loginPage.isAt(); // we have not logged in, so we should be redirected
        loginPage.fillAndSignIn(newUserEmail, newUserPWD);
        projectPage.go();
        projectPage.isAt();
        projectPage.goToLogoutPageViaMenu();
        loginPage.isAt();
        goTo(projectPage);
        loginPage.isAt();
    }


    /*
    @Override
    public WebDriver getDefaultDriver() {
        return new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
        //return new ChromeDriver();
    }
    */

    /*
    @Override
    public String getDefaultBaseUrl() {
        return baseUrl;
    }
    */
}