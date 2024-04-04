package resources;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browsers {

    public WebDriver getDriver(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        if (browser == null){
            System.setProperty("webdriver.chrome.driver", "src//test//java//resources//chromedriver.exe");
            return new ChromeDriver(options);
        }
         switch (browser.toLowerCase()) {
             case "chrome":
                 System.setProperty("webdriver.chrome.driver", "src//test//java//resources//chromedriver.exe");
                 return new ChromeDriver(options);
             case "yandex":
                 System.setProperty("webdriver.chrome.driver", "src//test//java//resources//yandexdriver.exe");
                 return new ChromeDriver(options);
             default:
                 throw new IllegalArgumentException("Unsupported browser");
         }
    }
}
