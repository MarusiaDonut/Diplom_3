package tests;

import ru.praktikum.burgers.api.model.ResponseUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.burgers.page.object.CrossToMainPageBurgers;
import ru.praktikum.burgers.page.object.CrossToProfilePageBurgers;

public class CrossingToMainTest {
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
        baseTest.deleteUser(responseUser);
    }
}
