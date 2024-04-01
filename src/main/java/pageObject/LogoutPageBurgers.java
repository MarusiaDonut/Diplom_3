package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPageBurgers {
    private final WebDriver driver;
    public LogoutPageBurgers(WebDriver driver) {
        this.driver = driver;
    }
    private final By logoutButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[3]/button");

    private final By loginText = By.xpath(".//div[@class='Auth_login__3hAey']/h2");

    @Step("click on button logout")
    public void logoutClick() {
        driver.findElement(logoutButton).click();
    }

    @Step("check displayed login page")
    public String displayedLoginPage() {
        WebElement authorizationElement = driver.findElement(loginText);
        authorizationElement.isDisplayed();
        return authorizationElement.getText();
    }
}
