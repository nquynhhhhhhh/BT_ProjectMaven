package Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUI {
    //khai báo biến cục bộ
    private static WebDriver driver;

    //khởi tạo hàm để “đưa driver từ Base Test vào class WebUI”
    public WebUI(WebDriver driver) {
        this.driver = driver;
    }

    public static void click(By by) {
        System.out.println("Click on element: " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(by)); //sẵn sàng có thể click
        driver.findElement(by).click();
    }

    public static void sendKeys(By by, String text) {
        System.out.println("Set text: " + text + " on element " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(text);
    }

    public static void clear(By by) {
        System.out.println("Clear text on element: " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).clear();
    }

    public static String getText(By by){
        System.out.println("Get text of element: " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String text = driver.findElement(by).getText();
        System.out.println("==> Text: " + text);
        return  driver.findElement(by).getText();
    }

    public static boolean isDisplayed(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).isDisplayed();
    }

    public static boolean waitForElementNotPresent(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));

            //chờ đến khi element biến mất / không tồn tại => trả về true
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

        } catch (TimeoutException e) {
            //sau timeout vẫn còn =>  false
            return false;
        }
    }

}
