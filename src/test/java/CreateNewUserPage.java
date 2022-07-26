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
    private static SelenideElement genderDropdownElement = createUserFormElement.$(By.xpath("./div[2]/div/div[2]"));
    private static SelenideElement calenderDate = $(By.xpath("/html/body/app-root/app-layout/div/app-create-user/div/form/div[3]/div/div[1]/app-single-datepicker/div/div/ngb-datepicker/div[2]/div/ngb-datepicker-month/div[7]/div[7]/span"));
    private static SelenideElement datePicker = $(By.xpath("/html/body/app-root/app-layout/div/app-create-user/div/form/div[3]/div/div[1]/app-single-datepicker/div/div/input"));

    public static SelenideElement getCalenderDate() {
        return calenderDate;
    }

    public static SelenideElement getDatePicker() {
        return datePicker;
    }
// Dropdown dropdown = new Dropdown(genderDropdownElement, "./label", "./app-filter-gender/ng-select/div/span", "./app-filter-gender/ng-select/ng-dropdown-panel/div/div[2]");

    public static SelenideElement getFirstNameInputBox() {
        return firstNameInputBox;
    }

    public static SelenideElement getLastNameInputBox() {
        return lastNameInputBox;
    }

    public static SelenideElement getEmailInputBox() {
        return emailInputBox;
    }

    public static SelenideElement getGenderDropdownArrowButton() {
        return genderDropdownArrowButton;
    }

    public static SelenideElement getGenderMaleButton() {
        return genderMaleButton;
    }

    public static SelenideElement getRoleDropdownArrowButton() {
        return roleDropdownArrowButton;
    }

    public static SelenideElement getRoleDrafterCheckbox() {
        return roleDrafterCheckbox;
    }

    public static SelenideElement getCountryDropdownArrowButton() {
        return countryDropdownArrowButton;
    }

    public static SelenideElement getCountryArmeniaButton() {
        return countryArmeniaButton;
    }

    public static SelenideElement getCityDropdownArrowButton() {
        return cityDropdownArrowButton;
    }

    public static SelenideElement getCreateUserButton() {
        return createUserButton;
    }

}
