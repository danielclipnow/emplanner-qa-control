import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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



public class DashboardWorker{
    public int delayTime = Main.delayTime;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public Logger log = Main.log;

    public DashboardWorker(WebDriver driver) {
        PageFactory.initElements(driver, this);
        DashboardWorker.driver = driver;
    }

    public void testDashboard() throws InterruptedException{
        BaseClass baseClass = new BaseClass(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        TimeUnit.SECONDS.sleep(10);
        long timewidget = System.currentTimeMillis();

        try {
            driver.findElement(By.xpath("//a[@class='badge sc-new']"));
            driver.findElement(By.xpath("//div[@class='main d-flex align-items-end']"));

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("widget not loaded within "+delayTime+ "seconds");
        }
        long timewidget1 = System.currentTimeMillis();
        long diff = (timewidget1 - timewidget)/1000;
        log.log(Level.INFO, "The order overdue loading time is "+diff+" seconds");
        baseClass.dashboard.click();
        TimeUnit.SECONDS.sleep(delayTime);
        log.log(Level.INFO, "switched to dashboard");
        try {
            baseClass.order.click();
            try {
                TimeUnit.SECONDS.sleep(delayTime);
                driver.findElement(By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']"));
                baseClass.close.click();
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                log.log(Level.INFO,"performers not loaded within " + delayTime + " seconds");
                TimeUnit.SECONDS.sleep(delayTime);
                baseClass.close.click();
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.log(Level.INFO, "orders not found");

        }

        log.log(Level.INFO, "go to profile page");

    }



}

