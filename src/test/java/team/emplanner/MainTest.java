package team.emplanner;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static WebDriver driver;
    private static final Logger log = LogManager.getLogger(MainTest.class);
    private static WebDriverWait wait;
    @BeforeAll
    public static void OSValidator() {
        System.out.println("os.name: "+ OS);
        log.log(Level.INFO, "os.name: "+ OS);
        if (OS.startsWith("mac os")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        } else if (OS.startsWith("unix")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_linux");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.emplanner.team");
        log.log(Level.INFO, "open emplanner");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        }
    @Test
    public void testEmail() {
        log.log(Level.INFO, "test starts");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        log.log(Level.INFO, "page loaded");
        WebElement username = driver.findElement(By.id("email"));
        username.sendKeys(ConfProperties.getProperty("login"));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(ConfProperties.getProperty("password"));
        WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block']"));
        button.click();
        log.log(Level.INFO, "go to test profile");
    }
    @Test
    public void testLocalStorage () throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        LocalStorage local = ((WebStorage)driver).getLocalStorage();
        local.setItem("newRelease","1.9");
        String localItem = local.getItem("newRelease");
        System.out.println(localItem);
        TimeUnit.SECONDS.sleep(5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary m-0 ml-1 px-3']")));
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String tabHandle = it.next();
        WebElement popup = driver.findElement(By.xpath("//button[@class='btn btn-primary m-0 ml-1 px-3']"));
        popup.click();
        TimeUnit.SECONDS.sleep(5);
        driver.switchTo().window(tabHandle);
        String localItem2 = local.getItem("newRelease");
        System.out.println(localItem2);
        TimeUnit.SECONDS.sleep(10);
        log.log(Level.INFO, "switched to first tab");

        try {
            driver.findElement(By.xpath("//a[@class='badge sc-new']"));
            driver.findElement(By.xpath("//div[@class='main d-flex align-items-end']"));

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("widget not loaded within 10 seconds");
            teardown();
        }
        WebElement dashboard = driver.findElement(By.xpath("//a[@class='nav-link']"));
        dashboard.click();
        TimeUnit.SECONDS.sleep(5);
        log.log(Level.INFO, "switched to dashboard");

        WebElement order = driver.findElement(By.xpath("//button[@class='btn btn-action btn-assign mr-1']"));
        order.click();
        TimeUnit.SECONDS.sleep(10);
        try {
            driver.findElement(By.xpath("//div[@class='ng-option']"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("performers not loaded within 10 seconds");
            teardown();
        }
        WebElement close = driver.findElement(By.xpath("//button[@class='modal-close']"));
        close.click();

        TimeUnit.SECONDS.sleep(5);
        WebElement user = driver.findElement(By.xpath("//span[@class='user-initials']"));
        user.click();
        log.log(Level.INFO, "go to profile page");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='firstName']")));
        String firstname = driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).getAttribute("value");
        String lastname = driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).getAttribute("value");
        String expectedHeading = firstname.substring(0,1).concat(lastname.substring(0,1));
        log.log(Level.INFO, "get initials");
        String heading = driver.findElement(By.xpath("//span[@class='user-initials']")).getText();
        assertEquals(heading, expectedHeading);
        log.log(Level.INFO, "compare values");
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
        log.log(Level.INFO, "exit browser");

    }

}