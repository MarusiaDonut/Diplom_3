package tests;

import ru.praktikum.burgers.api.model.ResponseUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.burgers.page.object.CrossToProfilePageBurgers;
import ru.praktikum.burgers.page.object.LogoutPageBurgers;

public class LogoutTest {
    WebDriver driver;
    ResponseUser responseUser = new ResponseUser();
    BaseTest baseTest = new BaseTest();
    @Before
    public void startTest() {
        responseUser = baseTest.createUser();

       driver = baseTest.loginUser();

        CrossToProfilePageBurgers crossToProfilePageBurgers = new CrossToProfilePageBurgers(driver);
        crossToProfilePageBurgers.headerClick();
    }

    @Test
    public void checkLogout() {
        LogoutPageBurgers logoutPageBurgers = new LogoutPageBurgers(driver);
        logoutPageBurgers.logoutClick();
        String getTextLoginPage = logoutPageBurgers.displayedLoginPage();
        Assert.assertEquals("Вход", getTextLoginPage);
    }

    @After
    public void teardown() {
        driver.quit();
        baseTest.deleteUser(responseUser);
    }
}
