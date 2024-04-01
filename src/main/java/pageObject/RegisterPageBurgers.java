package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPageBurgers {
    private final WebDriver driver;
    public RegisterPageBurgers(WebDriver driver) {
        this.driver = driver;
    }
    private final By loginButton = By.xpath("//*[@id='root']/div/main/div/form/button[text()='Войти']");
    private final By warningText = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");
    private final By nameField = By.xpath(".//fieldset[1]/div/div/input[contains(@class, 'input__textfield')]");
    private final By emailField = By.xpath(".//fieldset[2]/div/div/input[contains(@class, 'input__textfield')]");
    private final By passwordField = By.xpath(".//fieldset[3]/div/div/input[contains(@class, 'input__textfield')]");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    @Step("fill dates about user in register page")
    public void fillRegisterField(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("click on register button")
    public void registerClick() {
        driver.findElement(registerButton).click();
    }

    @Step("wait login page")
    public void waitForLoginData() {
        new WebDriverWait(driver, 15).until(driver -> (driver.findElement(loginButton).getText() != null
        ));
    }

    @Step("check displayed login page")
    public String displayedLoginPage() {
        WebElement loginElement = driver.findElement(loginButton);
        loginElement.isDisplayed();
        return loginElement.getText();
    }

    @Step("get warning test")
    public String getWarningTextPage() {
        WebElement text = driver.findElement(warningText);
        return text.getText();
    }

    @Step("check displayed register page")
    public String displayedRegisterPage() {
        WebElement registerElement = driver.findElement(registerButton);
        registerElement.isDisplayed();
        return registerElement.getText();
    }
}
