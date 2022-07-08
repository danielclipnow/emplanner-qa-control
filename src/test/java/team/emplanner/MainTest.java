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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainTest {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static WebDriver driver;
    private static final Logger log = LogManager.getLogger(MainTest.class);
    private static WebDriverWait wait;
    public static final int delayTime = 5;

    @BeforeAll
    public static void OSValidator() {

        System.out.println("os.name: " + OS);
        log.log(Level.INFO, "os.name: " + OS);
        if (OS.startsWith("mac os")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        } else if (OS.startsWith("linux")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_linux");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.emplanner.team");
        log.log(Level.INFO, "open emplanner");
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        }
    @Test
    public void testEmail() {
        BaseClass baseClass = new BaseClass(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        log.log(Level.INFO, "test starts");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        log.log(Level.INFO, "page loaded");
        baseClass.username.sendKeys(ConfProperties.getProperty("login"));
        baseClass.password.sendKeys(ConfProperties.getProperty("password"));
        baseClass.button.click();
        log.log(Level.INFO, "go to test profile");
    }
    @Test
    public void testLocalStorage () throws InterruptedException {
        TestDashboard testDashboard = new TestDashboard(driver);
        testDashboard.localStorage();
        BaseClass baseClass = new BaseClass(driver);
        log.log(Level.INFO, "switched to first tab");

        try {
            driver.findElement(By.xpath("//a[@class='badge sc-new']"));
            driver.findElement(By.xpath("//div[@class='main d-flex align-items-end']"));

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("widget not loaded within "+delayTime+ "seconds");
            teardown();
        }
        baseClass.dashboard.click();
        TimeUnit.SECONDS.sleep(delayTime);
        log.log(Level.INFO, "switched to dashboard");

        baseClass.order.click();
        TimeUnit.SECONDS.sleep(delayTime);
        try {
            driver.findElement(By.xpath("//div[@class='ng-option']"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("performers not loaded within" +delayTime+ "seconds");
            teardown();
        }
        baseClass.close.click();
        log.log(Level.INFO, "go to profile page");

        testDashboard.userPage();
        testDashboard.schedule();
    }


    @AfterAll
    public static void teardown() {
        TestDashboard.driver.quit();
        log.log(Level.INFO, "exit browser");

    }

}
