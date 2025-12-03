package BT1;

import Common.BaseTest;
import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        System.out.println("Header Name of table: " + headerTable.size() + " items");
        for (int i = 0; i < headerTable.size(); i++) {
            System.out.println(headerTable.get(i).getText());
        }

        System.out.println("===========");

        Thread.sleep(2000);
        List<WebElement> itemMyBear = driver.findElements(By.xpath("//tbody//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[contains(@style, 'table-cell')]"));
        Assert.assertEquals(driver.findElement(By.xpath(Locators.itemMyBear)).getText(), "My bear", "Name does not match");
        Thread.sleep(1000);
        //xpath từ My bear => sau My Bear là 0
        softAssert.assertEquals(itemMyBear.get(0).getText(), "Kids & Toy", "Parent Category does not match");
        softAssert.assertEquals(itemMyBear.get(1).getText(), "1", "Order Level does not match");
        softAssert.assertEquals(itemMyBear.get(2).getText(), "1", "Level does not match");
        //thuộc tính src thuộc thẻ <img> nhưng itemMyBear chỉ lấy đến thẻ <td> do đó phải .findElement(By.tagName("img"))
        softAssert.assertEquals(itemMyBear.get(3).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/cSoDjVnjVAQwb1lMbm5vd3kHCSaq2nRwKnW6Adwh.webp", "Image Banner does not match");
        softAssert.assertEquals(itemMyBear.get(4).getText(), "—", "Icon does not null");
        softAssert.assertEquals(itemMyBear.get(5).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/cSoDjVnjVAQwb1lMbm5vd3kHCSaq2nRwKnW6Adwh.webp", "Cover Image does not match");

        //muốn check isSeclect thì phải có thẻ input => kh dùng List do chỉ bắt đến thẻ td
        boolean itemFeatured = driver.findElement(By.xpath(Locators.toggleFeatured)).isSelected(); //chú ý xpath, toggle Featured là vị trí số 7
        softAssert.assertFalse(itemFeatured, "Featured is ON");
        if (itemFeatured == false) {
            driver.findElement(By.xpath(Locators.toggleFeaturedClick)).click();
            Thread.sleep(1000);
            softAssert.assertEquals(driver.findElement(By.xpath(Locators.dialogFeatured)).getText(), "Featured categories updated successfully", "Dialog does not match");
            boolean itemFeatured2 = driver.findElement(By.xpath(Locators.toggleFeatured)).isSelected(); //cập nhật sau khi click
            softAssert.assertTrue(itemFeatured2, "Featured is OFF");
            Thread.sleep(1000);
            driver.findElement(By.xpath(Locators.toggleFeaturedClick)).click();
        }

        Thread.sleep(1000);
        //điền biến boolean itemFeatured vào => kiểm tra giá trị hiện tại của toggle
        boolean itemHotCategory = driver.findElement(By.xpath(Locators.toggleHotCategory)).isSelected();
        softAssert.assertTrue(itemHotCategory, "Hot Category is OFF");
        if (itemHotCategory == true) {
            driver.findElement(By.xpath(Locators.toggleHotCategoryClick)).click();
            Thread.sleep(1000);
            softAssert.assertEquals(driver.findElement(By.xpath(Locators.dialogHotCategory)).getText(), "Hot categories updated successfully", "Dialog does not match");
            boolean itemHotCategory2 = driver.findElement(By.xpath(Locators.toggleHotCategory)).isSelected();
            softAssert.assertFalse(itemHotCategory2, "Hot Category is ON");
            Thread.sleep(1000);
            driver.findElement(By.xpath(Locators.toggleHotCategoryClick)).click();
        }

        Thread.sleep(2000);
        WebElement optionsDelete = driver.findElement(By.xpath(Locators.buttonDelete));
        softAssert.assertEquals(optionsDelete.getAttribute("title"), "Delete", "Button Delete does not match");
        driver.findElement(By.xpath(Locators.buttonDelete)).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.titleDialog)).getText(), "Delete Confirmation", "Title dialog Delete does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.textDialog)).getText(), "Are you sure to delete this?", "Text dialog does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.buttonDeleteDialog)).getText(), "Delete", "Button Delete on dialog does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.buttonCancelDialog)).getText(), "Cancel", "Button Cancel on dialog does not match");
        driver.findElement(By.xpath(Locators.buttonCancelDialog)).click();

        Thread.sleep(2000);
        WebElement optionsEdit = driver.findElement(By.xpath(Locators.buttonEdit));
        softAssert.assertEquals(optionsEdit.getAttribute("title"), "Edit", "Button Edit does not match");
        Thread.sleep(2000);
        softAssert.assertAll();
    }

    @Test
    public void itemMyBear2() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath(Locators.menuProducts)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.menuCategory)).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.headerCategoryPage)).getText(), "All categories", "Header does not match");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).sendKeys("My bear 2");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).submit();
        Thread.sleep(1000); //submit gây ra việc reload lại trang => cần thêm time để load
        System.out.println("Item: " + driver.findElement(By.xpath(Locators.itemMyBear2)).getText());
    }

    @Test
    public void dashboardPOS() throws InterruptedException {
        driver.findElement(By.xpath(Locators.menuPOS)).click();
        driver.findElement(By.xpath("//span[normalize-space()='POS Manager']")).click();
        Thread.sleep(1000);
    }

}




