import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Schedule {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public int delayTime = Main.delayTime;
    public Logger log = Main.log;

    public Schedule(WebDriver driver) {
        PageFactory.initElements(driver, this);
        Schedule.driver = driver;
    }
    public void testSchedule() throws InterruptedException {
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
        long diff = (time1 - time)/1000;
        log.log(Level.INFO, "The schedule loading time is "+diff);
        baseClass.week.click();
        TimeUnit.SECONDS.sleep(delayTime);
    }
}
