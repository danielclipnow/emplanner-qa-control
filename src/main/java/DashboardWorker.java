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
        log.log(Level.INFO, "switched to the first tab");
        String localItem2 = local.getItem("newRelease");
        System.out.println(localItem2);
        TimeUnit.SECONDS.sleep(delayTime);
        long timewidget = System.currentTimeMillis();

        try {
            driver.findElement(By.xpath("//a[@class='badge sc-new']"));
            driver.findElement(By.xpath("//div[@class='main d-flex align-items-end']"));

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("widget not loaded within "+delayTime+ "seconds");
            BaseClass.teardown();
        }
        long timewidget1 = System.currentTimeMillis();
        long diff = (timewidget1 - timewidget)/1000;
        log.log(Level.INFO, "The order overdue loading time is "+diff+" seconds");
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
            BaseClass.teardown();
        }
        baseClass.close.click();
        log.log(Level.INFO, "go to profile page");

    }



}

