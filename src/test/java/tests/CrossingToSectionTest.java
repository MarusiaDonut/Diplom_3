package tests;

import api.UserAPI;
import classes.ResponseUser;
import classes.User;
import constants.Browser;
import constants.Link;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.CrossToSectionBurgers;
import pageObject.LoginPageBurgers;
import resources.Browsers;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class CrossingToSectionTest {
    WebDriver driver;
    UserAPI userAPI = new UserAPI();
    User user = new User("test_ui1@yandex.ru", "123456", "TestMashaLoginUser");
    ResponseUser responseUser = new ResponseUser();
    private final String section;
    Browsers browsers = new Browsers();

    public CrossingToSectionTest(String section) {
        this.section = section;
    }
    @Before
    public void startTest() {
        RestAssured.baseURI = Link.MAIN_PAGE;
        Response response = userAPI.sendPostRequestCreateUser(user);
        responseUser = response.body().as(ResponseUser.class);

        driver = browsers.getDriver(Browser.YANDEX);
        driver.get(Link.MAIN_PAGE);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        LoginPageBurgers loginPageBurgers = new LoginPageBurgers(driver);
        loginPageBurgers.headerLoginClick();
        loginPageBurgers.fillLoginField("test_ui1@yandex.ru", "123456");
        loginPageBurgers.loginClick();
        loginPageBurgers.waitForAuthorizationData();
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Булки"},
                {"Соусы"},
                {"Начинки"},
        };
    }

    @Test
    public void checkCrossToSections() {
        CrossToSectionBurgers crossToSectionBurgers = new CrossToSectionBurgers(driver);
        crossToSectionBurgers.sectionClick(section);
        String getTextSection = crossToSectionBurgers.displayedSection(section);
        Assert.assertEquals(section, getTextSection);
    }

    @After
    public void teardown() {
        driver.quit();
        if (responseUser.getAccessToken() != null) {
            userAPI.sendDeleteRequestUser(responseUser.getAccessToken());
        }
    }
}
