package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement usernameField = $("#user-name");
    public SelenideElement passwordField =  $("#password");
    public SelenideElement loginButtonField = $("#login-button");
    public SelenideElement errorMessageField = $("h3");
    public void enterUsername(String username){
        usernameField.setValue(username);
    }
    public void enterPassword(String password){
        passwordField.setValue(password);
    }
    public void clickLoginButton (){
        loginButtonField.click();
    }
    public String getErrorMessage (){
        return errorMessageField.getText();
    }
}
