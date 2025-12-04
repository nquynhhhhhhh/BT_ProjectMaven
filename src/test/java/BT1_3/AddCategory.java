package BT1_3;

import Common.BaseTest;
import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        Thread.sleep(2000);
        List<WebElement> itemMyBear2 = driver.findElements(By.xpath("//tbody//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[contains(@style, 'table-cell')]"));
        Assert.assertEquals(driver.findElement(By.xpath(Locators.itemMyBear2)).getText(), "My Bear 2", "Name does not match");
        Thread.sleep(1000);
        //xpath từ My bear => sau My Bear là 0
        softAssert.assertEquals(itemMyBear2.get(0).getText(), "My bear", "Parent Category does not match");
        softAssert.assertEquals(itemMyBear2.get(1).getText(), "2", "Order Level does not match");
        softAssert.assertEquals(itemMyBear2.get(2).getText(), "2", "Level does not match");
        //thuộc tính src thuộc thẻ <img> nhưng itemMyBear chỉ lấy đến thẻ <td> do đó phải .findElement(By.tagName("img"))
        softAssert.assertEquals(itemMyBear2.get(3).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/5uGGc0F8UdZxgLaV2fZqNeQWoWsKkgQ1dPzcud6D.webp", "Image Banner does not match");
        softAssert.assertEquals(itemMyBear2.get(4).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/dJTEYgrrLFhXwYEmB8MsYqotXvhCBUaCA4rGUgEt.jpg", "Icon does not null");
        softAssert.assertEquals(itemMyBear2.get(5).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/A0cQLCcDugNg75Qu0jGKqiNlNZvH0ehzpIT2l75M.webp", "Cover Image does not match");

        boolean itemFeaturedMB2 = driver.findElement(By.xpath(Locators.toggleFeaturedMB2)).isSelected(); //chú ý xpath, toggle Featured là vị trí số 7
        softAssert.assertTrue(itemFeaturedMB2, "Featured is OFF");
        if (itemFeaturedMB2 == true) {
            driver.findElement(By.xpath(Locators.toggleFeaturedClickMB2)).click();
            Thread.sleep(1000);
            softAssert.assertEquals(driver.findElement(By.xpath(Locators.dialogFeatured)).getText(), "Featured categories updated successfully", "Dialog does not match");
            boolean itemFeatured2 = driver.findElement(By.xpath(Locators.toggleFeaturedMB2)).isSelected(); //cập nhật sau khi click
            softAssert.assertFalse(itemFeatured2, "Featured is ON");
            Thread.sleep(1000);
            driver.findElement(By.xpath(Locators.toggleFeaturedClickMB2)).click();
        }

        Thread.sleep(1000);
        //điền biến boolean itemFeatured vào => kiểm tra giá trị hiện tại của toggle
        boolean itemHotCategory = driver.findElement(By.xpath(Locators.toggleHotCategoryMB2)).isSelected();
        softAssert.assertFalse(itemHotCategory, "Hot Category is ON");
        if (itemHotCategory == false) {
            driver.findElement(By.xpath(Locators.toggleHotCategoryClickMB2)).click();
            Thread.sleep(1000);
            softAssert.assertEquals(driver.findElement(By.xpath(Locators.dialogHotCategory)).getText(), "Hot categories updated successfully", "Dialog does not match");
            boolean itemHotCategory2 = driver.findElement(By.xpath(Locators.toggleHotCategoryMB2)).isSelected();
            softAssert.assertTrue(itemHotCategory2, "Hot Category is OFF");
            Thread.sleep(1000);
            driver.findElement(By.xpath(Locators.toggleHotCategoryClickMB2)).click();
        }
        Thread.sleep(2000);
        WebElement optionsDelete = driver.findElement(By.xpath(Locators.buttonDeleteMB2));
        softAssert.assertEquals(optionsDelete.getAttribute("title"), "Delete", "Button Delete does not match");
        driver.findElement(By.xpath(Locators.buttonDeleteMB2)).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.titleDialog)).getText(), "Delete Confirmation", "Title dialog Delete does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.textDialog)).getText(), "Are you sure to delete this?", "Text dialog does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.buttonDeleteDialog)).getText(), "Delete", "Button Delete on dialog does not match");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.buttonCancelDialog)).getText(), "Cancel", "Button Cancel on dialog does not match");
        driver.findElement(By.xpath(Locators.buttonCancelDialog)).click();

        Thread.sleep(2000);
        WebElement optionsEdit = driver.findElement(By.xpath(Locators.buttonEditMB2));
        softAssert.assertEquals(optionsEdit.getAttribute("title"), "Edit", "Button Edit does not match");
        Thread.sleep(2000);
        softAssert.assertAll();

    }

    @Test
    public void editMyBear2() throws InterruptedException {
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

        driver.findElement(By.xpath(Locators.buttonEditMB2)).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.headerEditPage)).getText(), "Category Information", "Header does not match");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.inputName)).getAttribute("value"), "My Bear 2", "Item does not match");
        driver.findElement(By.xpath(Locators.buttonParentCategory)).click();
        driver.findElement(By.xpath(Locators.inputParentCategory)).sendKeys("Kids & Toy");
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.itemKidsToy)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.inputOrderingNumber)).clear();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.inputOrderingNumber)).sendKeys("1");
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement button = driver.findElement(By.xpath(Locators.buttonRemoveCoverImage));
//        js.executeScript("arguments[0].scrollIntoView(false);", button);
//        Thread.sleep(1000);
//        button.click();

        WebElement save = driver.findElement(By.xpath(Locators.buttonSave));
        js.executeScript("arguments[0].scrollIntoView(false);", save);
        Thread.sleep(1000);
        save.click();
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.dialogSaveSuccess)).getText(), "Category has been updated successfully", "Dialog Save Success does not match");

        //bởi vì menu Product đang mở r nên kh cần click lại
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.menuCategory)).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.headerCategoryPage)).getText(), "All categories", "Header does not match");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).sendKeys("My bear 2");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).submit();
        Thread.sleep(1000); //submit gây ra việc reload lại trang => cần thêm time để load
        System.out.println("Item: " + driver.findElement(By.xpath(Locators.itemMyBear2)).getText());

        Thread.sleep(2000);
        List<WebElement> itemMyBear2 = driver.findElements(By.xpath("//tbody//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[contains(@style, 'table-cell')]"));
        Assert.assertEquals(driver.findElement(By.xpath(Locators.itemMyBear2)).getText(), "My Bear 2", "Name does not match");
        Thread.sleep(1000);
        softAssert.assertEquals(itemMyBear2.get(0).getText(), "Kids & Toy", "Parent Category does not match");
        softAssert.assertEquals(itemMyBear2.get(1).getText(), "1", "Order Level does not match");
        softAssert.assertEquals(itemMyBear2.get(2).getText(), "1", "Level does not match");
        //thuộc tính src thuộc thẻ <img> nhưng itemMyBear chỉ lấy đến thẻ <td> do đó phải .findElement(By.tagName("img"))
        softAssert.assertEquals(itemMyBear2.get(3).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/5uGGc0F8UdZxgLaV2fZqNeQWoWsKkgQ1dPzcud6D.webp", "Image Banner does not match");
        softAssert.assertEquals(itemMyBear2.get(4).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/dJTEYgrrLFhXwYEmB8MsYqotXvhCBUaCA4rGUgEt.jpg", "Icon does not null");
        softAssert.assertEquals(itemMyBear2.get(5).findElement(By.tagName("img")).getAttribute("src"), "https://ecommerce.anhtester.com/public/uploads/all/A0cQLCcDugNg75Qu0jGKqiNlNZvH0ehzpIT2l75M.webp", "Cover Image does not match");


        //EDIT LẠI ĐỂ KH ẢNH HƯỞNG TEST itemMyBear2
        driver.findElement(By.xpath(Locators.buttonEditMB2)).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.inputName)).getAttribute("value"), "My Bear 2", "Item does not match");
        driver.findElement(By.xpath(Locators.buttonParentCategory)).click();
        driver.findElement(By.xpath(Locators.inputParentCategory)).sendKeys("My bear");
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.itemParentMB)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.inputOrderingNumber)).clear();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.inputOrderingNumber)).sendKeys("2");
        Thread.sleep(1000);
        WebElement save2 = driver.findElement(By.xpath(Locators.buttonSave));
        js.executeScript("arguments[0].scrollIntoView(false);", save2);
        Thread.sleep(1000);
        save2.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Locators.menuCategory)).click();

        softAssert.assertAll();
    }

    @Test
    public void deleteItem() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath(Locators.inputSearchMenuDashboard)).sendKeys("Category");
        driver.findElement(By.xpath(Locators.menuCategory)).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath(Locators.headerCategoryPage)).getText(), "All categories", "Header does not match");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).sendKeys("Book");
        driver.findElement(By.xpath(Locators.inputSearchCategory)).submit();
        Thread.sleep(1000); //submit gây ra việc reload lại trang => cần thêm time để load

        Assert.assertEquals(driver.findElement(By.xpath(Locators.itemBook)).getText(), "Book", "Name does not match");
        driver.findElement(By.xpath(Locators.buttonDeleteBook)).click();
        Thread.sleep(1000);
//        driver.findElement(By.xpath(Locators.buttonDeleteDialog)).click();

    }

}




