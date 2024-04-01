package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageBurgers {
    private final WebDriver driver;
    public LoginPageBurgers(WebDriver driver) {
        this.driver = driver;
    }

    private final By bodyLoginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By authorizationButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By headerLoginButton = By.xpath("//*[@id='root']/div/header/nav/a");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By emailField = By.xpath(".//fieldset[1]/div/div/input[contains(@class, 'input__textfield')]");
    private final By passwordField = By.xpath(".//fieldset[2]/div/div/input[contains(@class, 'input__textfield')]");
    private final By authorizationPage = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/h1");

    @Step("click on login button 'Войти в аккаунт'")
    public void bodyLoginClick() {
        driver.findElement(bodyLoginButton).click();
    }

    @Step("click on login button 'Личный кабинет'")
    public void headerLoginClick() {
        driver.findElement(headerLoginButton).click();
    }

    @Step("click on login button in register page")
    public void loginLinkClick() {
        driver.findElement(loginLink).click();
    }

    @Step("wait authorization page")
    public void waitForAuthorizationData() {
        new WebDriverWait(driver,  10).until(driver -> (driver.findElement(authorizationPage).getText() != null
        ));
    }

    @Step("fill dates for login")
    public void fillLoginField(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("click on login button 'Войти'")
    public void loginClick() {
        driver.findElement(loginButton).click();
    }

    @Step("check displayed authorization page")
    public String displayedAuthorizationPage() {
        WebElement authorizationElement = driver.findElement(authorizationButton);
        waitForAuthorizationData();
        authorizationElement.isDisplayed();
        return authorizationElement.getText();
    }
}
