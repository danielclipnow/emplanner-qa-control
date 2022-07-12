import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class UserProfilePage {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public int delayTime = Main.delayTime;
    public Logger log = Main.log;

    public UserProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        UserProfilePage.driver = driver;
    }
    public void testUserPage() throws InterruptedException {
        BaseClass baseClass = new BaseClass(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(delayTime));
        TimeUnit.SECONDS.sleep(delayTime);
        baseClass.user.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='firstName']")));
        String firstname = driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).getAttribute("value");
        String lastname = driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).getAttribute("value");
        String expectedHeading = firstname.substring(0,1).concat(lastname.substring(0,1));
        String heading = driver.findElement(By.xpath("//span[@class='user-initials']")).getText();
        if (heading.equals(expectedHeading)){
            log.log(Level.INFO, "Values are equal");
        } else {
            log.log(Level.INFO, "Values are not equal");
        }
    }
}
