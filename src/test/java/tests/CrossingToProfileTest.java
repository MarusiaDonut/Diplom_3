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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.CrossToProfilePageBurgers;
import pageObject.LoginPageBurgers;
import resources.Browsers;

import java.util.concurrent.TimeUnit;

public class CrossingToProfileTest {
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
    }

    @Test
    public void checkCrossToProfilePage() {
        CrossToProfilePageBurgers crossToProfilePageBurgers = new CrossToProfilePageBurgers(driver);
        crossToProfilePageBurgers.headerClick();
        String getTextActiveButton = crossToProfilePageBurgers.displayedProfilePage();
        Assert.assertEquals("Профиль", getTextActiveButton);
    }

    @After
    public void teardown() {
        driver.quit();
        if (responseUser.getAccessToken() != null) {
            userAPI.sendDeleteRequestUser(responseUser.getAccessToken());
        }
    }
}
