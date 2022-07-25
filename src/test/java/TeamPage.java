import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TeamPage {
    private static SelenideElement teamPageHeader = $(By.xpath("/html/body/app-root/app-layout/div/app-team/div[1]/div/app-team-filters/form/div[1]"));
    private static SelenideElement countryDropdownArrowButton = $(By.xpath("./app-filter-country/div/ng-select/div/span"));
    private static SelenideElement createNewUserButton = $(By.xpath("./div/a[2]"));
}
