import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateNewUserPage {
    private static SelenideElement createUserFormElement = $(By.xpath("/html/body/app-root/app-layout/div/app-create-user/div/form"));
    private static SelenideElement firstNameInputBox = createUserFormElement.$(By.xpath("./div[1]/div/div[1]/input"));
    private static SelenideElement lastNameInputBox = createUserFormElement.$(By.xpath("./div[1]/div/div[2]/input"));
    private static SelenideElement emailInputBox = createUserFormElement.$(By.xpath("./div[2]/div/div[1]/input"));
    private static SelenideElement genderDropdownArrowButton = createUserFormElement.$(By.xpath("./div[2]/div/div[2]/app-filter-gender/ng-select/div/span"));
    private static SelenideElement genderMaleButton = createUserFormElement.$(By.xpath("./div[2]/div/div[2]/app-filter-gender/ng-select/ng-dropdown-panel/div/div[2]/div[1]"));
    private static SelenideElement roleDropdownArrowButton = createUserFormElement.$(By.xpath("./div[3]/div/div[2]/app-filter-roles/div/ng-select/div/span"));
    private static SelenideElement roleDrafterCheckbox = createUserFormElement.$(By.xpath("./div[3]/div/div[2]/app-filter-roles/div/ng-select/ng-dropdown-panel/div/div[2]/div[3]"));
    private static SelenideElement countryDropdownArrowButton = createUserFormElement.$(By.xpath("./div[4]/div/div[1]/app-filter-country/ng-select/div/span"));
    private static SelenideElement countryArmeniaButton = createUserFormElement.$(By.xpath("./div[4]/div/div[1]/app-filter-country/ng-select/ng-dropdown-panel/div/div[2]/div[1]"));
    private static SelenideElement cityDropdownArrowButton = createUserFormElement.$(By.xpath("./div[4]/div/div[2]/ng-select/div/span"));
    private static SelenideElement createUserButton = createUserFormElement.$(By.xpath("./div[6]/button"));

}
