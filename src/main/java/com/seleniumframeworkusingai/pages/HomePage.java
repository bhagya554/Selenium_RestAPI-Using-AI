package com.seleniumframeworkusingai.pages;

import com.seleniumframeworkusingai.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "a[href='/products']")
    private WebElement productsLink;

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginLink;

    @FindBy(css = "a[href='/contact_us']")
    private WebElement contactUsLink;

    public HomePage navigateToHomePage(String baseUrl) {
        navigateTo(baseUrl);
        return this;
    }

    public ProductsPage goToProductsPage() {
        click(productsLink);
        return new ProductsPage();
    }

    public LoginPage goToLoginPage() {
        click(signupLoginLink);
        return new LoginPage();
    }

    public ContactUsPage goToContactUsPage() {
        click(contactUsLink);
        return new ContactUsPage();
    }

    public boolean isAtHomePage() {
        return getPageTitle().contains("Automation Exercise");
    }
}
