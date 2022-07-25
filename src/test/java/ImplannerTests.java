import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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
    @Test
    void authenticate(){
        ImplannerSteps.setLogin("lev.kashcheev@docusketch.com");
        ImplannerSteps.setPassword("VK27fRrIHzyM");
        ImplannerSteps.mlcOnSignInButton();
        Selenide.sleep(5000);
    }
    @AfterEach
    void tearDown(){
        Selenide.closeWebDriver();
    }
}
