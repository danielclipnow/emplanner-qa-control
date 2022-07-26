import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AppHeader {
    private static SelenideElement teamButton = $(By.xpath("/html/body/app-root/app-layout/app-header/div/app-nav/nav/a[6]"));

    public static SelenideElement getTeamButton() {
        return teamButton;
    }
}
