package com.seleniumframeworkusingai.pages;

import com.seleniumframeworkusingai.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement emailInput;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect')]")
    private WebElement loginErrorMessage;

    public LoginPage login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginButton);
        return this;
    }

    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginErrorMessage);
    }

    public HomePage loginSuccess(String email, String password) {
        login(email, password);
        return new HomePage();
    }
}
