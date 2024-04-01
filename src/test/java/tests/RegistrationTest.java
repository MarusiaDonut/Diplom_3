package tests;

import api.UserAPI;
import classes.ResponseUser;
import classes.User;
import constants.Browser;
import constants.Link;
import io.restassured.response.Response;
import org.junit.Assert;
import pageObject.RegisterPageBurgers;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import resources.Browsers;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    WebDriver driver;
    UserAPI userAPI = new UserAPI();
    User user = new User("test_ui1@yandex.ru", "123456");
    ResponseUser responseUser = new ResponseUser();
    Browsers browsers = new Browsers();

    @Before
    public void startTest() {
        driver = browsers.getDriver(Browser.YANDEX);
        driver.get(Link.REGISTER_PAGE);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        RestAssured.baseURI = Link.MAIN_PAGE;
    }

    @Test
    public void checkSuccessfulRegister()  {
        RegisterPageBurgers registerPageBurgers = new RegisterPageBurgers(driver);
        registerPageBurgers.fillRegisterField("Maria1", "test_ui1@yandex.ru", "123456");
        registerPageBurgers.registerClick();
        registerPageBurgers.waitForLoginData();
        String loginElement = registerPageBurgers.displayedLoginPage();
        Assert.assertEquals("Войти", loginElement);
    }

    @Test
    public void checkUnsuccessfulRegister() {
        RegisterPageBurgers registerPageBurgers = new RegisterPageBurgers(driver);
        registerPageBurgers.fillRegisterField("Maria1", "test_ui1@yandex.ru", "12345");
        registerPageBurgers.registerClick();
        String warningText = registerPageBurgers.getWarningTextPage();
        Assert.assertEquals("Некорректный пароль", warningText);
        String registerButton = registerPageBurgers.displayedRegisterPage();
        Assert.assertEquals("Зарегистрироваться", registerButton);
    }

    @After
    public void teardown() {
        driver.quit();
        Response response = userAPI.sendPostRequestLoginCourier(user);
        responseUser = response.body().as(ResponseUser.class);
        if (responseUser.getAccessToken() != null) {
            userAPI.sendDeleteRequestUser(responseUser.getAccessToken());
        }
    }
}
