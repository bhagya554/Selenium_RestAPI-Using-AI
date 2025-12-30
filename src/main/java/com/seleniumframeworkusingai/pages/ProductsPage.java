package com.seleniumframeworkusingai.pages;

import com.seleniumframeworkusingai.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = ".features_items .product-image-wrapper")
    private List<WebElement> productCards;

    @FindBy(css = "input[name='search']")
    private WebElement searchInput;

    @FindBy(css = "button#submit_search")
    private WebElement searchButton;

    public boolean isProductsListVisible() {
        return productCards.size() > 0;
    }

    public ProductDetailPage clickFirstProduct() {
        productCards.get(0).click();
        return new ProductDetailPage();
    }

    public ProductsPage searchForProduct(String productName) {
        type(searchInput, productName);
        click(searchButton);
        return this;
    }
}
