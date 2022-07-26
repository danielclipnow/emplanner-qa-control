import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static SelenideElement authenticationFormElement = $(By.xpath("/html/body/app-root/app-auth/div/div/form"));
    private static SelenideElement loginField = authenticationFormElement.$(By.xpath("./div[2]/div[1]/input"));
    private static SelenideElement passwordField = authenticationFormElement.$(By.xpath("./div[2]/div[2]/input"));
    private static SelenideElement authenticationErrorLabel = authenticationFormElement.$(By.xpath("./div[3]/div"));
    private static SelenideElement signInButton = authenticationFormElement.$(By.xpath("./div[4]/button"));
    private static SelenideElement popUp = $(By.xpath("/html/body/ngb-modal-window/div/div/div[3]/button"));

    public static SelenideElement getPopUp() {
        return popUp;
    }

    public static SelenideElement typeIntoLoginField (String login){
        loginField.setValue(login);
        return loginField;
    }
    public static SelenideElement typeIntoPasswordField (String password){
        passwordField.setValue(password);
        return passwordField;
    }
    public static SelenideElement getSignInButton(){
        return signInButton;
    }
}
