package ru.praktikum.burgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CrossToMainPageBurgers {
    private final WebDriver driver;
    public CrossToMainPageBurgers(WebDriver driver) {
        this.driver = driver;
    }
    private final By constructorLink = By.className("AppHeader_header__link__3D_hX");
    private final By logoLink = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By mainPage = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/h1");


    @Step("click on button 'Конструктор'")
    public void constructorClick() {
        driver.findElement(constructorLink).click();
    }

    @Step("click on button logo")
    public void logoClick() {
        driver.findElement(logoLink).click();
    }

    @Step("check displayed main page")
    public String displayedMainPage() {
        WebElement authorizationElement = driver.findElement(mainPage);
        authorizationElement.isDisplayed();
        return authorizationElement.getText();
    }
}
