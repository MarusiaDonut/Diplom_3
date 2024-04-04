package tests;

import ru.praktikum.burgers.api.model.ResponseUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.burgers.page.object.CrossToProfilePageBurgers;

public class CrossingToProfileTest {
    WebDriver driver;
    ResponseUser responseUser = new ResponseUser();

    BaseTest baseTest = new BaseTest();

    @Before
    public void startTest() {
        responseUser = baseTest.createUser();

        driver = baseTest.loginUser();
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
        baseTest.deleteUser(responseUser);
    }
}
