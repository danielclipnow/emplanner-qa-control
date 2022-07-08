package team.emplanner;

import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static team.emplanner.MainTest.delayTime;


public class TestDashboard {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public TestDashboard(WebDriver driver) {
        PageFactory.initElements(driver, this);
        TestDashboard.driver = driver;
    }

    @Test
    public void schedule() throws InterruptedException {
        BaseClass baseClass = new BaseClass(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        TimeUnit.SECONDS.sleep(delayTime);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdownMenuButton")));
        baseClass.schedule.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/ui/admin/schedule']")));
        baseClass.team.click();
        long time = System.currentTimeMillis();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class ='dropdown-toggle pseudo-link']")));
        driver.findElement(By.xpath("//button[@class ='dropdown-toggle pseudo-link']"));
        long time1 = System.currentTimeMillis();
        long diff = time1 - time;
        System.out.println(diff);
        baseClass.week.click();
        TimeUnit.SECONDS.sleep(delayTime);
    }
    public void userPage() throws InterruptedException {
        BaseClass baseClass = new BaseClass(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        TimeUnit.SECONDS.sleep(delayTime);
        baseClass.user.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='firstName']")));
        String firstname = driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).getAttribute("value");
        String lastname = driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).getAttribute("value");
        String expectedHeading = firstname.substring(0,1).concat(lastname.substring(0,1));
        String heading = driver.findElement(By.xpath("//span[@class='user-initials']")).getText();
        assertEquals(heading, expectedHeading);
    }
    public void localStorage () throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        BaseClass baseClass = new BaseClass(driver);
        TimeUnit.SECONDS.sleep(delayTime);
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
        String localItem2 = local.getItem("newRelease");
        System.out.println(localItem2);
        TimeUnit.SECONDS.sleep(delayTime);
    }

}
