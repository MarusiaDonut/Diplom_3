package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import resources.Browsers;
import ru.praktikum.burgers.api.client.UserAPI;
import ru.praktikum.burgers.api.model.ResponseUser;
import ru.praktikum.burgers.api.model.User;
import ru.praktikum.burgers.constants.Browser;
import ru.praktikum.burgers.constants.Link;
import ru.praktikum.burgers.page.object.CrossToProfilePageBurgers;
import ru.praktikum.burgers.page.object.LoginPageBurgers;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    UserAPI userAPI = new UserAPI();
    User user = new User("test_ui1@yandex.ru", "123456", "TestMashaLoginUser");
    ResponseUser responseUser = new ResponseUser();
    Browsers browsers = new Browsers();
    public ResponseUser createUser() {
        RestAssured.baseURI = Link.MAIN_PAGE;
        Response response = userAPI.sendPostRequestCreateUser(user);
        responseUser = response.body().as(ResponseUser.class);
        return responseUser;
    }

    public void deleteUser(ResponseUser responseUser) {
        driver.quit();
        if (responseUser.getAccessToken() != null) {
            userAPI.sendDeleteRequestUser(responseUser.getAccessToken());
        }
    }

    public WebDriver loginUser() {
        driver = browsers.getDriver(Browser.YANDEX);
        driver.get(Link.MAIN_PAGE);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        LoginPageBurgers loginPageBurgers = new LoginPageBurgers(driver);
        loginPageBurgers.headerLoginClick();
        loginPageBurgers.fillLoginField("test_ui1@yandex.ru", "123456");
        loginPageBurgers.loginClick();
        loginPageBurgers.waitForAuthorizationData();

        return driver;
    }
}
