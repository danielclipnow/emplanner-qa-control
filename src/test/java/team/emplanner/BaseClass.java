package team.emplanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {
    public static String OS = System.getProperty("os.name").toLowerCase();
    public static WebDriver driver;
    public static WebDriverWait wait;
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

    @FindBy(xpath = "//button[@class='btn btn-action btn-assign mr-1']")
    public WebElement order;

    @FindBy(xpath = "//button[@class='modal-close']")
    public WebElement close;

    @FindBy(xpath = "//span[@class='user-initials']")
    public WebElement user;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    public WebElement week;


    public static void osDriver() {
        System.out.println("os.name: " + OS);
        if (OS.startsWith("mac os")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        } else if (OS.startsWith("linux")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_linux");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.emplanner.team");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));


    }



}

