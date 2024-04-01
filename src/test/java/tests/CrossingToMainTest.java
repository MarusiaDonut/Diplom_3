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
import org.openqa.selenium.WebDriver;
import pageObject.CrossToMainPageBurgers;
import pageObject.CrossToProfilePageBurgers;
import pageObject.LoginPageBurgers;
import resources.Browsers;

import java.util.concurrent.TimeUnit;

public class CrossingToMainTest {
    WebDriver driver;
    UserAPI userAPI = new UserAPI();
    User user = new User("test_ui1@yandex.ru", "123456", "TestMashaLoginUser");
    ResponseUser responseUser = new ResponseUser();
    Browsers browsers = new Browsers();
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

        CrossToProfilePageBurgers crossToProfilePageBurgers = new CrossToProfilePageBurgers(driver);
        crossToProfilePageBurgers.headerClick();
    }

    @Test
    public void checkCrossToMainPageFromConstructor() {
        CrossToMainPageBurgers crossToMainPageBurgers = new CrossToMainPageBurgers(driver);
        crossToMainPageBurgers.constructorClick();
        String getTextMainPage = crossToMainPageBurgers.displayedMainPage();
        Assert.assertEquals("Соберите бургер", getTextMainPage);
    }

    @Test
    public void checkCrossToMainPageFromLogo() {
        CrossToMainPageBurgers crossToMainPageBurgers = new CrossToMainPageBurgers(driver);
        crossToMainPageBurgers.logoClick();
        String getTextMainPage = crossToMainPageBurgers.displayedMainPage();
        Assert.assertEquals("Соберите бургер", getTextMainPage);
    }

    @After
    public void teardown() {
        driver.quit();
        if (responseUser.getAccessToken() != null) {
            userAPI.sendDeleteRequestUser(responseUser.getAccessToken());
        }
    }
}
