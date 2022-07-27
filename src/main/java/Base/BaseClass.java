package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
    public static WebDriver driver;
    public BaseClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        BaseClass.driver = driver;
    }


    @FindBy(id = "email" )
    public WebElement username;

    @FindBy(id = "password" )
    public WebElement password;

    @FindBy(id = "dropdownMenuButton")
    public WebElement schedule;

    @FindBy(xpath = "//*[contains(@href, '/ui/admin/schedule')]")
    public WebElement team;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
    public WebElement button;

    @FindBy(xpath = "//button[@class='btn btn-primary m-0 ml-1 px-3']")
    public WebElement popup;
    @FindBy(xpath = "//a[@class='nav-link']")
    public WebElement dashboard;

    @FindBy(xpath = "//a[@href='/ui/work']")
    public WebElement work;

    @FindBy(xpath = "//button[@class='btn btn-action btn-assign mr-1']")
    public WebElement order;

    @FindBy(xpath = "//button[@class='modal-close']")
    public WebElement close;

    @FindBy(xpath = "//span[@class='user-initials']")
    public WebElement user;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    public WebElement week;

    @FindBy(xpath = "//app-filter-roles[@controlname='role']")
    public WebElement rolesList;

    @FindBy(xpath="//span[text()='Drafter']")
    public WebElement Drafter;

    @FindBy(xpath="//span[text()='ESX Drafter']")
    public WebElement ESXDrafter;

    @FindBy(xpath="//span[text()='ESX Reviewer']")
    public WebElement ESXReviewer;

    @FindBy(xpath="//span[text()='Reviewer']")
    public WebElement Reviewer;


    @FindBy(xpath="//div[@class='title text-uppercase']")
    public WebElement timeTrackerWidget;

    @FindBy(xpath="/html/body/app-root/app-layout/div/app-work/div[1]/div/app-orders-analytics-widget/div/div/div[1]/div[1]")
    public WebElement ordersAnalyticsWidget;

    @FindBy(xpath="//div[@class='badge mb-1 sc-await_esx_review']")
    public WebElement AwaitESXReviewOrderWidget;

    @FindBy(xpath="//div[@class='badge mb-1 sc-esx_needed']")
    public WebElement ESXNeededOrderWidget;

    @FindBy(xpath="//div[@class='title text-uppercase mb-1']")
    public WebElement completedOrdersWidget;

    @FindBy(xpath="//div[@class='badge mb-1 sc-new']")
    public WebElement NewOrderWidget;

    @FindBy(xpath="//div[@class='badge mb-1 sc-await_review']")
    public WebElement AwaitReviewOrderWidget;


    @FindBy(xpath="//button[@class='btn btn-primary btn-wide']")
    public WebElement savechanges;

    @FindBy(tagName="body")
    public WebElement body;












}

