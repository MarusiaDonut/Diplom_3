package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class CrossToSectionBurgers {
    private final WebDriver driver;
    public CrossToSectionBurgers(WebDriver driver) {
        this.driver = driver;
    }
    private final By bunsSection = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[1]");
    private final By sauceSection = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[2]");
    private final By toppingSection = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[3]");
    String name;
    private final By bunHeader = By.xpath(".//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[1]");

    private final By sauceHeader = By.xpath(".//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[2]");

    private final By toppingHeader = By.xpath(".//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[3]");



    @Step("click on button section")
    public void sectionClick(String section) {
        if (Objects.equals(section, "Булки")) {
            driver.findElement(sauceSection).click();
            driver.findElement(bunsSection).click();
        }
        else if (Objects.equals(section, "Соусы")) {
            driver.findElement(sauceSection).click();
        }
        else if (Objects.equals(section, "Начинки")) {
            driver.findElement(toppingSection).click();
        }
        name = section;
    }

    @Step("check displayed section")
    public String displayedSection(String section) {
        WebElement authorizationElement = null;
        if (Objects.equals(section, "Булки")) {
            authorizationElement = driver.findElement(bunsSection);
        }
        else if (Objects.equals(section, "Соусы")) {
            authorizationElement = driver.findElement(sauceHeader);
        }
        else if (Objects.equals(section, "Начинки")) {
            authorizationElement = driver.findElement(toppingSection);
        }
        authorizationElement.isDisplayed();
        return authorizationElement.getText();
    }
}
