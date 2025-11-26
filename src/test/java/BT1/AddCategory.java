package BT1;

import Common.BaseTest;
import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddCategory extends BaseTest {

    @Test (priority = 1)
    public void menuDashboard() throws InterruptedException {
        driver.findElement(By.xpath(Locators.inputSearchMenuDashboard)).sendKeys("Category");
        driver.findElement(By.xpath(Locators.menuCategory)).click();
        Thread.sleep(1000);
        System.out.println("Header Category: " + driver.findElement(By.xpath(Locators.headerCategoryPage)).getText());
        driver.findElement(By.xpath(Locators.inputSearchCategory)).sendKeys("My bear");
        System.out.println("Item: " + driver.findElement(By.xpath(Locators.itemMyBear)).getText());
    }

    @Test (priority = 2)
    public void dashboardPOS() throws InterruptedException {
        driver.findElement(By.xpath(Locators.menuPOS)).click();
        driver.findElement(By.xpath("//span[normalize-space()='POS Manager']")).click();
        Thread.sleep(1000);
    }
}




