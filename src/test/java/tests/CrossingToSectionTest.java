package tests;

import ru.praktikum.burgers.api.model.ResponseUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.praktikum.burgers.page.object.CrossToSectionBurgers;

@RunWith(Parameterized.class)
public class CrossingToSectionTest {
    WebDriver driver;
    ResponseUser responseUser = new ResponseUser();
    private final String section;
    BaseTest baseTest = new BaseTest();

    public CrossingToSectionTest(String section) {
        this.section = section;
    }
    @Before
    public void startTest() {
        responseUser = baseTest.createUser();

        driver = baseTest.loginUser();
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
        baseTest.deleteUser(responseUser);
    }
}
