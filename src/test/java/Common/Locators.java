package Common;

public class Locators {
    //Login page
    public static String inputEmail = "//input[@id='email']";
    public static String inputPassword = "//input[@id='password']";
    public static String buttonLogin = "//button[normalize-space()='Login']";
    public static String checkboxRememberMe = "//input[@name='remember']";
    public static String linkForgotPassword = "//u[normalize-space()='Forgot password?']";
    public static String alertErrorMessage = "//span[normalize-space()='Invalid login credentials']";

    //Dashboard page
    public static String tabDashboard = "//a[normalize-space()='Orders']/preceding-sibling::a";
    public static String tabOrders = "//a[normalize-space()='Orders']";
    public static String buttonAddNewDashboard = "//span[normalize-space()='Add New']/parent::span";
    public static String userNameDashboard = "//a[@data-toggle='dropdown']//span[normalize-space()='Admin Example']";

    public static String inputSearchMenuDashboard = "//input[@id='menu-search']";
    public static String menuPOS = "//span[normalize-space()='POS System']";
    public static String menuProducts = "//span[normalize-space()='Products']";
    public static String menuPreorder = "//span[normalize-space()='Preorder']";
    public static String menuNotes = "//span[normalize-space()='Notes']";
    public static String menuCategory = "//span[normalize-space()='Category']";

    //Category
    public static String headerCategoryPage = "//h1[normalize-space()='All categories']";
    public static String headerTableCategories = "//h5[normalize-space()='Categories']";
    public static String buttonAddNewCategory = "//span[normalize-space()='Add New category']";
    public static String inputSearchCategory = "//input[@id='search']";
    public static String itemMyBear = "//td[normalize-space()='My bear']";
    public static String toggleFeatured = "//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[7]//input";
    public static String toggleFeaturedClick = "//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[7]/label";
    public static String dialogFeatured = "//span[normalize-space()='Featured categories updated successfully']";
    public static String toggleHotCategory= "//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[8]//input";
    public static String toggleHotCategoryClick= "//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[8]/label";
    public static String dialogHotCategory = "//span[normalize-space()='Hot categories updated successfully']";
    public static String buttonDelete= "//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[9]/a[@title='Delete']";
    public static String buttonEdit= "//td[normalize-space()='My bear' and @class='d-flex align-items-center']/following-sibling::td[9]/a[@title='Edit']";

    public static String itemMyBear2 = "//td[normalize-space()='My Bear 2']";
    public static String toggleFeaturedMB2 = "//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[7]//input";
    public static String toggleFeaturedClickMB2 = "//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[7]/label";
    public static String toggleHotCategoryMB2= "//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[8]//input";
    public static String toggleHotCategoryClickMB2= "//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[8]/label";
    public static String buttonDeleteMB2= "//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[9]/a[@title='Delete']";
    public static String buttonEditMB2= "//td[normalize-space()='My Bear 2' and @class='d-flex align-items-center']/following-sibling::td[9]/a[@title='Edit'][1]";

    public static String titleDialog= "//h4[normalize-space()='Delete Confirmation']";
    public static String buttonCancelDialog= "//button[normalize-space()='Cancel']";
    public static String buttonDeleteDialog= "//a[normalize-space()='Delete']";
    public static String textDialog= "//p[normalize-space()='Are you sure to delete this?']";

    public static String itemBook = "//td[normalize-space()='Book']";
    public static String buttonDeleteBook = "//td[normalize-space()='Book' and @class='d-flex align-items-center']/following-sibling::td[9]/a[@title='Delete']";
    public static String dialogDeleteSuccess = "//span[@data-notify='message' and contains(normalize-space(), 'Category has been deleted successfully')]";
    public static String dialogAddSuccess = "";

    //Edit Category
    public static String headerEditPage = "//h5[normalize-space()='Category Information']";
    public static String inputName = "//input[@id='name']";
    public static String buttonParentCategory= "//label[normalize-space()='Parent Category']/following-sibling::div//button";
    public static String inputParentCategory = "//label[normalize-space()='Parent Category']/following-sibling::div//input";
    public static String itemKidsToy= "//label[normalize-space()='Parent Category']/following-sibling::div//span[normalize-space()='Kids & Toy']";
    public static String itemParentMB= "//label[normalize-space()='Parent Category']/following-sibling::div//span[normalize-space()='-- My bear']";
    public static String inputOrderingNumber= "//label[normalize-space()='Ordering Number']/following-sibling::div/input";
    public static String buttonRemoveCoverImage= "//label[normalize-space()='Cover Image']/following-sibling::div//button[contains(@class,'remove-attachment')]";
    public static String buttonSave= "//button[normalize-space()='Save']";
    public static String dialogSaveSuccess= "//span[normalize-space()='Category has been updated successfully']";



    //Add New Category
    public static String headerAddNewCategory = "//h5[normalize-space()='Category Information']";
    public static String inputNameBook = "//input[@id='name']";
//    public static String buttonType = "//button[@title='Physical']";
//    public static String buttonParentCategory = "//div[contains(text(),'No Parent')]";
//    public static String inputOrderingNumber = "//input[@id='order_level']";
//    public static String inputBannerImage = "//input[@name='banner']";
//    public static String inputIconImage = "//input[@name='icon']";
//    public static String inputCoverImage = "//input[@name='cover_image']";
//    public static String inputMetaTitle = "//input[@placeholder='Meta Title']";
//    public static String inputMetaDescription = "//textarea[@name='meta_description']";
//    public static String inputMetaKeywords = "//textarea[@placeholder='Keyword, Keyword']";
//    public static String buttonFilteringAttributes = "//button[@title='Nothing selected']";
//    public static String buttonSave = "//button[normalize-space()='Save']";


}
