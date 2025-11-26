package Common;

public class Locators {
    //Login page
    public static String headerLogin = "//h1[normalize-space()='Welcome to eCommerce CMS']";
    public static String inputEmail = "//input[@id='email']";
    public static String inputPassword = "//input[@id='password']";
    public static String buttonLogin = "//button[normalize-space()='Login']";
    public static String checkboxRememberMe = "//input[@name='remember']";
    public static String linkForgotPassword = "//u[normalize-space()='Forgot password?']";
    public static String alertErrorMessage = "//span[normalize-space()='Invalid login credentials']";

    //Dashboard page
    public static String inputSearchMenuDashboard = "//input[@id='menu-search']";
    public static String menuPOS = "//span[normalize-space()='POS System']";
    public static String menuProducts = "//span[normalize-space()='Products']";
    public static String menuPreorder = "//span[normalize-space()='Preorder']";
    public static String menuNotes = "//span[normalize-space()='Notes']";
    public static String menuCategory = "//ul[@id='search-menu']//span[normalize-space()='Category']";

    //Category
    public static String headerCategoryPage = "//h1[normalize-space()='All categories']";
    public static String buttonAddNewCategory = "//span[normalize-space()='Add New category']";
    public static String inputSearchCategory = "//input[@id='search']";
    public static String itemMyBear = "//td[normalize-space()='My bear']";


    //Add New Category
    public static String headerAddNewCategory = "//h5[normalize-space()='Category Information']";
    public static String inputName = "//input[@id='name']";
    public static String buttonType = "//button[@title='Physical']";
    public static String buttonParentCategory = "//div[contains(text(),'No Parent')]";
    public static String inputOrderingNumber = "//input[@id='order_level']";
    public static String inputBannerImage = "//input[@name='banner']";
    public static String inputIconImage = "//input[@name='icon']";
    public static String inputCoverImage = "//input[@name='cover_image']";
    public static String inputMetaTitle = "//input[@placeholder='Meta Title']";
    public static String inputMetaDescription = "//textarea[@name='meta_description']";
    public static String inputMetaKeywords = "//textarea[@placeholder='Keyword, Keyword']";
    public static String buttonFilteringAttributes = "//button[@title='Nothing selected']";
    public static String buttonSave = "//button[normalize-space()='Save']";


}
