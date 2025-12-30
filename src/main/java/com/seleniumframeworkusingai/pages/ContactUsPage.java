package com.seleniumframeworkusingai.pages;

import com.seleniumframeworkusingai.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(css = "input[data-qa='name']")
    private WebElement nameInput;

    @FindBy(css = "input[data-qa='email']")
    private WebElement emailInput;

    @FindBy(css = "input[data-qa='subject']")
    private WebElement subjectInput;

    @FindBy(css = "textarea[data-qa='message']")
    private WebElement messageTextArea;

    @FindBy(css = "input[name='upload_file']")
    private WebElement uploadFileInput;

    @FindBy(css = "input[data-qa='submit-button']")
    private WebElement submitButton;

    @FindBy(css = "div.status.alert.alert-success")
    private WebElement successMessage;

    public ContactUsPage fillContactForm(String name, String email, String subject, String message, String filePath) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageTextArea, message);
        uploadFileInput.sendKeys(filePath);
        return this;
    }

    public ContactUsPage submitForm() {
        click(submitButton);
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }
}
