package BT1;

import Common.BaseTest;
import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AddCategory extends BaseTest {

    @Test
    public void dashboardPage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(driver.findElement(By.xpath(Locators.tabDashboard)).getText(), "Dashboard", "Tab title does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.tabOrders)).getText(), "Orders", "Tab title does not match");
        Thread.sleep(1000);
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.buttonAddNewDashboard)).getText(), "Add New", "Button does not match");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.userNameDashboard)).getText(), "Admin Example", "Username does not match");

        String dashboardColor = driver.findElement(By.xpath(Locators.tabDashboard)).getCssValue("color");
        String ordersColor = driver.findElement(By.xpath(Locators.tabOrders)).getCssValue("color");
        Assert.assertNotEquals(dashboardColor, ordersColor, "Dashboard and Orders must have different colors");
        softAssert.assertAll();
    }

    @Test
    public void categoryPage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath(Locators.inputSearchMenuDashboard)).sendKeys("Category");
        driver.findElement(By.xpath(Locators.menuCategory)).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.headerCategoryPage)).getText(), "All categories", "Header does not match");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).sendKeys("My bear");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).submit();
        Thread.sleep(1000); //submit gây ra việc reload lại trang => cần thêm time để load
        System.out.println("Item: " + driver.findElement(By.xpath(Locators.itemMyBear)).getText());
        List<WebElement> headerTable = driver.findElements(By.xpath("//tr[@class='footable-header']//th[contains(@style, 'table-cell')]"));
        System.out.println("Header Name of table: " + headerTable.size());
        for (int i = 0; i<headerTable.size(); i++){
            System.out.println(headerTable.get(i).getText());
        }

        Thread.sleep(2000);
        List<WebElement> itemMyBear = driver.findElements(By.xpath("//tbody//td[contains(@style, 'table-cell')]"));
        softAssert.assertEquals(itemMyBear.get(1).getText(), "My bear", "Name does not match");
        softAssert.assertEquals(itemMyBear.get(2).getText(), "Kids & Toy", "Parent Category does not match");
        softAssert.assertEquals(itemMyBear.get(3).getText(), "1", "Order Level does not match");
        softAssert.assertEquals(itemMyBear.get(4).getText(), "1", "Level does not match");
        softAssert.assertEquals(itemMyBear.get(6).getText(), "—","Icon does not null");

        //muốn check isSeclect thì phải có thẻ input => kh dùng List do chỉ bắt đến thẻ td
        Thread.sleep(2000);
        boolean itemFeatured = driver.findElement(By.xpath(Locators.toggleFeaturedFirst)).isSelected(); //TH này dùng kiểm tra ON/ OFF, enable là xem có thao tác đc trên đó ktra có bị vô hiệu hóa hay không
        softAssert.assertFalse(itemFeatured,  "Featured is ON");
        //điền biến boolean itemFeatured vào => kiểm tra giá trị hiện tại của toggle
        boolean itemHotCategory = driver.findElement(By.xpath(Locators.toggleHotCategory)).isSelected();
        softAssert.assertTrue(itemHotCategory, "Hot Category is OFF");


        softAssert.assertEquals(itemMyBear.get(10).getText(), "", "");

        Thread.sleep(2000);


        softAssert.assertAll();
    }

    @Test
    public void dashboardPOS() throws InterruptedException {
        driver.findElement(By.xpath(Locators.menuPOS)).click();
        driver.findElement(By.xpath("//span[normalize-space()='POS Manager']")).click();
        Thread.sleep(1000);
    }

}




