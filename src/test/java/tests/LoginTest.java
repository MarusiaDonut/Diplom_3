package tests;

import ru.praktikum.burgers.api.client.UserAPI;
import ru.praktikum.burgers.api.model.ResponseUser;
import ru.praktikum.burgers.api.model.User;
import ru.praktikum.burgers.constants.Browser;
import ru.praktikum.burgers.constants.Link;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.burgers.page.object.LoginPageBurgers;
import resources.Browsers;

import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class LoginTest {
    WebDriver driver;
    UserAPI userAPI = new UserAPI();
    Browsers browsers = new Browsers();
    User user = new User("test_ui1@yandex.ru", "123456", "TestMashaLoginUser");
    ResponseUser responseUser = new ResponseUser();
    BaseTest baseTest = new BaseTest();
    private final int button;
    private final String link;

    public LoginTest(int button, String link) {
        this.button = button;
        this.link = link;
    }

    @Before
    public void startTest() {
        RestAssured.baseURI = Link.MAIN_PAGE;
        Response response = userAPI.sendPostRequestCreateUser(user);
        responseUser = response.body().as(ResponseUser.class);

        driver = browsers.getDriver(Browser.YANDEX);
        driver.get(Link.MAIN_PAGE);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {1, Link.MAIN_PAGE},
                {2, Link.MAIN_PAGE},
                {3, Link.REGISTER_PAGE},
                {4, Link.FORGOT_PASSWORD_PAGE},
        };
    }

    @Test
    public void checkLogin() {
        driver.get(link);
        String textButton = getString();
        Assert.assertEquals("Оформить заказ", textButton);
    }

    private String getString() {
        LoginPageBurgers loginPageBurgers = new LoginPageBurgers(driver);
        if (button == 1) {
            loginPageBurgers.bodyLoginClick();
        }
        else if (button == 2) {
            loginPageBurgers.headerLoginClick();
        }
        else if (button == 3 || button == 4) {
            loginPageBurgers.loginLinkClick();
        }
        loginPageBurgers.fillLoginField(user.getEmail(), user.getPassword());
        loginPageBurgers.loginClick();
        return loginPageBurgers.displayedAuthorizationPage();
    }

    @After
    public void teardown() {
        driver.quit();
        if (responseUser.getAccessToken() != null) {
            userAPI.sendDeleteRequestUser(responseUser.getAccessToken());
        }
    }
}
