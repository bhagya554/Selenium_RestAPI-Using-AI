package com.seleniumframeworkusingai.pages;

import com.seleniumframeworkusingai.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {

    @FindBy(css = "h2:nth-child(1)")
    private WebElement productName;

    @FindBy(css = ".product_information .price")
    private WebElement productPrice;

    @FindBy(css = ".product_information .availability")
    private WebElement availabilityStatus;

    @FindBy(css = ".product_information .brand")
    private WebElement brandName;

    public String getProductName() {
        return waitForVisibility(productName).getText();
    }

    public String getPrice() {
        return waitForVisibility(productPrice).getText();
    }

    public String getAvailability() {
        return waitForVisibility(availabilityStatus).getText();
    }

    public String getBrandName() {
        return waitForVisibility(brandName).getText();
    }
}
