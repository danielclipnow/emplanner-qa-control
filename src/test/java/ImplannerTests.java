import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImplannerTests {
    @BeforeEach
    void setUp(){
        Configuration.headless = false;
        Configuration.browser = "chrome";
        //System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chrome");
        //Configuration.remote = "http://localhost:4444/wd/hub";
        //Configuration.driverManagerEnabled = false;
        Selenide.open("https://sandbox-ui.emplanner.team/ui/login");
    }
    //@Test
    void authenticate(){
        ImplannerSteps.setLogin("lev.kashcheev@docusketch.com");
        ImplannerSteps.setPassword("VK27fRrIHzyM");
        ImplannerSteps.clickOnSignInButton();
        ImplannerSteps.clickPopUpButton();
        Selenide.sleep(3000);
        Selenide.switchTo().window(0);
        Selenide.sleep(3000);
    }

    @Test
    void createTeamUser(){
        authenticate();
        ImplannerSteps.clickTeamButton();
        ImplannerSteps.clickCreateNewUserButton();
        ImplannerSteps.clickFirstNameInput();
        ImplannerSteps.typeIntoFirstNameInput("ATuser");
        ImplannerSteps.clickLastNameInput();
        ImplannerSteps.typeIntoLastNameInput("AT00003");
        ImplannerSteps.clickEmailInput();
        ImplannerSteps.typeIntoEmailInput("test3@mail.com");
        ImplannerSteps.clickGenderDropdownArrowButton();
        ImplannerSteps.clickGenderMaleButton();
        ImplannerSteps.clickDatePicker();
        ImplannerSteps.clickCalendarDate();
        ImplannerSteps.clickRoleDropdownArrowButton();
        ImplannerSteps.clickRoleDrafterCheckbox();
        ImplannerSteps.clickCountryDropdownArrowButton();
        ImplannerSteps.clickCountryArmeniaButton();
        Selenide.sleep(5000);
        ImplannerSteps.clickCreateUserButton();
        Selenide.sleep(5000);

    }
    @AfterEach
    void tearDown(){
        Selenide.closeWebDriver();
    }
}
