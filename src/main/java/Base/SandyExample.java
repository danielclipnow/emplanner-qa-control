package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SandyExample {
    public static WebDriver driverSandy;
    public static WebDriverWait wait;

    public SandyExample(WebDriver driverSandy) {
        PageFactory.initElements(driverSandy, this);
        SandyExample.driverSandy = driverSandy;
    }
    public void testSandy() throws InterruptedException{
        driverSandy.get("https://sandy.emplanner.team");
        wait = new WebDriverWait(driverSandy, Duration.ofSeconds(10));
        TimeUnit.SECONDS.sleep(5);
        BaseClass baseClass = new BaseClass(driverSandy);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        baseClass.username.sendKeys(ConfProperties.getProperty("login"));
        baseClass.password.sendKeys(ConfProperties.getProperty("password"));
        baseClass.button.click();
        TimeUnit.SECONDS.sleep(5);
        driverSandy.quit();
    }
}
