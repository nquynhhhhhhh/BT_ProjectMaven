package Common;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void createDriver() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.get("https://ecommerce.anhtester.com/login");
        driver.findElement(By.xpath(Locators.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(Locators.inputPassword)).sendKeys("123456");
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.buttonLogin)).click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void closeDriver() throws InterruptedException {
        driver.quit();
        Thread.sleep(2000);
    }

}
