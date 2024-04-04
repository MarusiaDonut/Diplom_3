package ru.praktikum.burgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CrossToProfilePageBurgers {
    private final WebDriver driver;
    public CrossToProfilePageBurgers(WebDriver driver) {
        this.driver = driver;
    }
    private final By headerButton = By.xpath("//p[text()='Личный Кабинет']/parent::a");
    private final By profilePage = By.xpath(".//a[contains(@class, 'Account_link_active__2opc9')]");

    @Step("click on button 'Личный кабинет'")
    public void headerClick() {
        driver.findElement(headerButton).click();
    }

    @Step("check displayed profile page")
    public String displayedProfilePage() {
        WebElement authorizationElement = driver.findElement(profilePage);
        authorizationElement.isDisplayed();
        return authorizationElement.getText();
    }
}
