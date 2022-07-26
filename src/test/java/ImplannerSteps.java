import io.qameta.allure.Step;

public class ImplannerSteps {
    @Step
    public static void setLogin(String login){
        LoginPage.typeIntoLoginField(login);
    }
    @Step
    public static void setPassword(String password){
        LoginPage.typeIntoPasswordField(password);
    }
    @Step
    public static void clickOnSignInButton(){
        LoginPage.getSignInButton().click();
    }
    @Step
    public static void clickPopUpButton(){
        LoginPage.getPopUp().click();
    }
    @Step
    public static void clickTeamButton(){
        AppHeader.getTeamButton().click();
    }
    @Step
    public static void clickCreateNewUserButton(){
        TeamPage.getCreateNewUserButton().click();
    }
    @Step
    public static void clickFirstNameInput(){
        CreateNewUserPage.getFirstNameInputBox().click();
    }
    @Step
    public static void typeIntoFirstNameInput(String firstName){
        CreateNewUserPage.getFirstNameInputBox().setValue(firstName);
    }
    @Step
    public static void clickLastNameInput(){
        CreateNewUserPage.getLastNameInputBox().click();
    }
    @Step
    public static void typeIntoLastNameInput(String lastName){
        CreateNewUserPage.getLastNameInputBox().setValue(lastName);
    }
    @Step
    public static void clickEmailInput(){
        CreateNewUserPage.getEmailInputBox().click();
    }
    @Step
    public static void typeIntoEmailInput(String email){
        CreateNewUserPage.getEmailInputBox().setValue(email);
    }
    @Step
    public static void clickGenderDropdownArrowButton(){
        CreateNewUserPage.getGenderDropdownArrowButton().click();
    }
    @Step
    public static void clickGenderMaleButton(){
        CreateNewUserPage.getGenderMaleButton().click();
    }
    @Step
    public static void clickDatePicker(){
        CreateNewUserPage.getDatePicker().click();
    }
    @Step
    public static void clickCalendarDate(){
        CreateNewUserPage.getCalenderDate().click();
    }
    @Step
    public static void clickRoleDropdownArrowButton(){
        CreateNewUserPage.getRoleDropdownArrowButton().click();
    }
    @Step
    public static void clickRoleDrafterCheckbox(){
        CreateNewUserPage.getRoleDrafterCheckbox().click();
    }
    @Step
    public static void clickCountryDropdownArrowButton(){
        CreateNewUserPage.getCountryDropdownArrowButton().click();
    }
    @Step
    public static void clickCountryArmeniaButton(){
        CreateNewUserPage.getCountryArmeniaButton().click();
    }
    @Step
    public static void clickCityDropdownArrowButton(){
        CreateNewUserPage.getCityDropdownArrowButton().click();
    }
    @Step
    public static void click(){

    }

    public static void clickCreateUserButton() {
        CreateNewUserPage.getCreateUserButton().click();
    }
}
