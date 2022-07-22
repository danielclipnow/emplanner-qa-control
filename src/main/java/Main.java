import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    public static WebDriverWait wait;
    public static WebDriver driver;
    public static WebDriver driver1;
    public static final int delayTime = 6;
    public static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("serverName",ConfProperties.getProperty("serverName"));
        System.setProperty("port",ConfProperties.getProperty("port"));
        osDriver();
        testEmail();
        if (args.length == 0) {
            DashboardWorker dashboardWorker = new DashboardWorker(driver);
            dashboardWorker.testDashboard();
            UserProfilePage userProfilePage = new UserProfilePage(driver);
            userProfilePage.testUserPage();
            Schedule schedule = new Schedule(driver);
            schedule.testSchedule();
            teardown();
            SlackIn.sendMessage("test completed successfully");
        }else if (args[0].startsWith("dash")) {
            DashboardWorker dashboardWorker = new DashboardWorker(driver);
            dashboardWorker.testDashboard();
            teardown();
            SlackIn.sendMessage("test dashboard completed successfully");

        } else {
            UserProfilePage userProfilePage = new UserProfilePage(driver);
            userProfilePage.testUserPage();
            Schedule schedule = new Schedule(driver);
            schedule.testSchedule();
            teardown();
            SlackIn.sendMessage("test userpage and schedule completed successfully");
        }


    }
    public static void osDriver() throws InterruptedException {
        
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
        driver.get("https://sandy.emplanner.team");
        log.log(Level.INFO, "open emplanner");
        driver1=new ChromeDriver(options);
        SandyExample sandy=new SandyExample(driver1);
        sandy.testSandy();
        

    }
    public static void testEmail() throws InterruptedException {
        BaseClass baseClass = new BaseClass(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        log.log(Level.INFO, "test starts");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        log.log(Level.INFO, "page loaded");
        baseClass.username.sendKeys(ConfProperties.getProperty("login"));
        baseClass.password.sendKeys(ConfProperties.getProperty("password"));
        baseClass.button.click();
        log.log(Level.INFO, "go to test profile");
        LocalStorage local = ((WebStorage)driver).getLocalStorage();
        local.setItem("newRelease","1.9");
        String localItem = local.getItem("newRelease");
        System.out.println(localItem);
        TimeUnit.SECONDS.sleep(delayTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary m-0 ml-1 px-3']")));
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String tabHandle = it.next();
        baseClass.popup.click();
        TimeUnit.SECONDS.sleep(delayTime);
        driver.switchTo().window(tabHandle);
        log.log(Level.INFO, "switched to the first tab");
        String localItem2 = local.getItem("newRelease");
        System.out.println(localItem2);
        TimeUnit.SECONDS.sleep(delayTime);
    }
    public static void teardown() {

        driver.quit();
        log.log(Level.INFO, "exit browser");
    }




}
