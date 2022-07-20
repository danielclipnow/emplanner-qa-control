import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import java.io.IOException;
import java.time.Duration;

public class Main {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static WebDriverWait wait;
    public static WebDriver driver;
    public static final int delayTime = 6;
    public static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        osDriver();
        testEmail();
        DashboardWorker dashboardWorker = new DashboardWorker(driver);
        dashboardWorker.testDashboard();
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.testUserPage();
        Schedule schedule = new Schedule(driver);
        schedule.testSchedule();
        teardown();
        SlackIn.sendMessage("test completed successfully");


    }
    public static void osDriver() {
        
        System.out.println("os.name: " + OS);
        log.log(Level.INFO, "os.name: " + OS);
        if (OS.startsWith("mac os")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        } else if (OS.startsWith("linux")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_linux");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
        driver.get("https://staging.emplanner.team");
        log.log(Level.INFO, "open emplanner");
        

    }
    public static void testEmail() {
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
    public static void teardown() {

        driver.quit();
        log.log(Level.INFO, "exit browser");
    }




}
