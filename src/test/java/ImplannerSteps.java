public class ImplannerSteps {
    public static void setLogin(String login){
        LoginPage.typeIntoLoginField(login);
    }
    public static void setPassword(String password){
        LoginPage.typeIntoPasswordField(password);
    }
    public static void mlcOnSignInButton(){
        LoginPage.getSignInButton().click();
    }

}
