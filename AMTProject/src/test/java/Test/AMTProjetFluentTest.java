package Test;

import Test.pages.HomePage;
import Test.pages.LoginPage;
import Test.pages.ProjectAddPage;
import Test.pages.ProjectPage;
import Test.pages.RegisterPage;
import io.probedock.client.annotations.ProbeTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.fluentlenium.core.annotation.Page;


import org.fluentlenium.core.FluentPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;


public class AMTProjetFluentTest extends FluentTest {
    /*
    `TESTING_FUNCTIONAL.md`
developer creates an account
developer logs in
developer creates 25 pages
developer browses the list of applications (3 pages of 10, 10 and 5 applications)
developer logs out
developer tries to go back to the list of applications and is redirected to login page

The tests should include assertions, so that changing the code (e.g. introducing a bug) breaks the code.

The report should describe and document a concrete example (with screenshots).
     */

    private final String baseUrl = "http://localhost:8080/AMT-Project/";

    @Page
    public LoginPage loginPage;

    @Page
    public HomePage homePage;

    @Page
    public ProjectAddPage projectAddPage;

    @Page
    public ProjectPage projectPage;

    @Page
    public RegisterPage registerPage;



    @Test
    @ProbeTest(tags = "WebUI")
    public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
        goTo(baseUrl);
        loginPage.isAt();
        loginPage.typeEmailAddress("not a valid email");
        loginPage.typePassword("any password");
        loginPage.clickSignin();
        loginPage.isAt();
    }

    @Test
    @ProbeTest(tags = "WebUI")
    public void successfulSigninShouldBringUserToHomePage() {
        goTo(baseUrl);
        loginPage.isAt();
        loginPage.typeEmailAddress("a@a.com");
        loginPage.typePassword("any password");
        loginPage.clickSignin();
        homePage.isAt();
    }

    @Test
    @ProbeTest(tags = "WebUI")
    public void itShouldBePossibleToGetProjectsListAfterSignin() {
        goTo(projectPage);
        loginPage.isAt(); // we have not logged in, so we should be redirected
        loginPage.typeEmailAddress("a@a.com");
        loginPage.typePassword("any password");
        loginPage.clickSignin();
        projectPage.isAt(); // we should be redirected toward the original target after signin
        //projectPage.clickOnNextPage();
        //projectPage.changeNumberOfRow(5);
    }


    @Override
    public WebDriver getDefaultDriver() {
        return new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
        //return new ChromeDriver();
    }

    @Override
    public String getDefaultBaseUrl() {
        return baseUrl;
    }

}