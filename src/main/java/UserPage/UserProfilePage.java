package UserPage;

import Base.BaseClass;
import Base.Main;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
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
        String expectedHeading = firstname.substring(0, 1).concat(lastname.substring(0, 1));
        String heading = driver.findElement(By.xpath("//span[@class='user-initials']")).getText();
        if (heading.equals(expectedHeading)) {
            log.log(Level.INFO, "Values are equal");
        } else {
            log.log(Level.INFO, "Values are not equal");
        }
        checkWorkWidgets("Reviewer,Drafter");
        checkWorkWidgets("ESX Drafter");
        checkWorkWidgets("ESX Drafter,ESX Reviewer");
        checkWorkWidgets("ESX Reviewer");

    }

    public void checkWorkWidgets(String role) throws InterruptedException {
        checkFilter();
        BaseClass baseClass = new BaseClass(driver);
        String[] roles = role.split(",");
        TimeUnit.SECONDS.sleep(delayTime);

        for (String s : roles) {
            TimeUnit.SECONDS.sleep(delayTime);
            String xpath = "//span[text()='" + s + "']";
            driver.findElement(By.xpath(xpath)).click();
        }
        TimeUnit.SECONDS.sleep(delayTime);
        saveChanges();
        TimeUnit.SECONDS.sleep(delayTime);
        reLogIn();
        TimeUnit.SECONDS.sleep(3);

        WebElement[] elems = new WebElement[]{baseClass.ESXNeededOrderWidget, baseClass.AwaitESXReviewOrderWidget, baseClass.AwaitReviewOrderWidget, baseClass.NewOrderWidget};
        ArrayList<String> widgets = new ArrayList<>(4);
        for (WebElement elem : elems) {
            try {
                elem.isDisplayed();
                widgets.add(elem.getText());

            } catch (NoSuchElementException e) {
                //e.printStackTrace();
            }

        }
        baseClass.user.click();
        System.out.println("for " + role + " are available widgets" + (widgets));
    }

    public void checkFilter() throws InterruptedException {
        BaseClass baseClass = new BaseClass(driver);
        String roles = driver.findElement(By.xpath("//*[contains(text(), 'Admin')]")).getText();
        String[] words = roles.split(",");

        if (words.length > 1) {
            baseClass.body.sendKeys(Keys.END);
            baseClass.rolesList.click();

            for (int i = 1; i < words.length; i++) {
                String word = words[i].trim();
                String xpath = "//span[text()='" + word + "']";
                TimeUnit.SECONDS.sleep(3);
                driver.findElement(By.xpath(xpath)).click();
            }
            TimeUnit.SECONDS.sleep(delayTime);
            saveChanges();
            reLogIn();
            baseClass.user.click();
            baseClass.body.sendKeys(Keys.END);
            baseClass.rolesList.click();
        }
        baseClass.body.sendKeys(Keys.END);
        baseClass.rolesList.click();

    }

    public void saveChanges() {
        BaseClass baseClass = new BaseClass(driver);

        try {
            baseClass.rolesList.click();
            baseClass.savechanges.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Profile has been saved successfully')]")));
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
            baseClass.savechanges.click();
        }
    }

    public void reLogIn() throws InterruptedException {
        BaseClass baseClass = new BaseClass(driver);
        try {
            baseClass.work.click();
            Main.testEmail();
        } catch (NoSuchElementException | InterruptedException e) {
            Main.testEmail();
        }
    }


}





