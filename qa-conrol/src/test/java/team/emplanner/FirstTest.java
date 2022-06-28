package team.emplanner;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest {
    public static WebDriver driver;
    private static final Logger log = LogManager.getLogger(FirstTest.class);


    @Test
    public void testEmail() throws InterruptedException {
        log.log(Level.INFO, "test starts");

        System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriver"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("loginpage"));
        log.log(Level.INFO, "open emplanner");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        log.log(Level.INFO, "page loaded");
        WebElement username = driver.findElement(By.id("email"));
        username.sendKeys(ConfProperties.getProperty("login"));

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(ConfProperties.getProperty("password"));

        WebElement button = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-block']"));
        button.click();
        log.log(Level.INFO, "go to test profile");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary m-0 ml-1 px-3']")));
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String tabHandle = it.next();
        WebElement popup = driver.findElement(By.xpath("//button[@class='btn btn-primary m-0 ml-1 px-3']"));
        popup.click();
        TimeUnit.SECONDS.sleep(5);
        driver.switchTo().window(tabHandle);
        TimeUnit.SECONDS.sleep(10);
        log.log(Level.INFO, "switched to first tab");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='nav-link']")));
        WebElement dashboard = driver.findElement(By.xpath("//a[@class='nav-link']"));
        dashboard.click();
        log.log(Level.INFO, "switched to dashboard");
        TimeUnit.SECONDS.sleep(5);

        WebElement user = driver.findElement(By.xpath("//span[@class='user-initials']"));
        user.click();
        log.log(Level.INFO, "go to profile page");
        TimeUnit.SECONDS.sleep(5);

        String firstname = driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).getAttribute("value");
        String lastname = driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).getAttribute("value");
        String expectedHeading = firstname.substring(0,1).concat(lastname.substring(0,1));
        log.log(Level.INFO, "get initials");
        String heading = driver.findElement(By.xpath("//span[@class='user-initials']")).getText();
        assertEquals(heading, expectedHeading);
        log.log(Level.INFO, "compare values");
        driver.quit();
        log.log(Level.INFO, "exit browser");
    }



}
