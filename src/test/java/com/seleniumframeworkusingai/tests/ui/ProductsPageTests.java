package com.seleniumframeworkusingai.tests.ui;

import com.seleniumframeworkusingai.pages.HomePage;
import com.seleniumframeworkusingai.pages.ProductDetailPage;
import com.seleniumframeworkusingai.pages.ProductsPage;
import com.seleniumframeworkusingai.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ProductsPageTests
 * -----------------
 * Contains UI test cases for validating the Products page on AutomationExercise.
 * Uses BaseTest for driver lifecycle and ConfigReader for environment data.
 */
public class ProductsPageTests extends BaseTest {

    @Test(description = "Verify user can view the list of products on the Products Page")
    public void verifyProductsListVisible() {
        // Navigate to Products Page from Home Page
        HomePage homePage = new HomePage();
        ProductsPage productsPage = homePage.goToProductsPage();

        Assert.assertTrue(productsPage.isProductsListVisible(),
                "❌ Product list is not visible on the Products page!");
    }

    @Test(description = "Verify user can view details of a specific product")
    public void verifyProductDetailsVisible() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = homePage.goToProductsPage();

        Assert.assertTrue(productsPage.isProductsListVisible(),
                "❌ Product list is not visible on Products page!");

        ProductDetailPage productDetailPage = productsPage.clickFirstProduct();

        String productName = productDetailPage.getProductName();
        String productPrice = productDetailPage.getPrice();

        Assert.assertTrue(productName.length() > 0, "❌ Product name is missing in Product Detail page!");
        Assert.assertTrue(productPrice.length() > 0, "❌ Product price is missing in Product Detail page!");

        System.out.println("✅ Verified Product Details: " + productName + " | " + productPrice);
    }

    @Test(description = "Verify user can search for a product by name")
    public void verifyProductSearch() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = homePage.goToProductsPage();

        productsPage.searchForProduct("Tshirt");

        Assert.assertTrue(productsPage.isProductsListVisible(),
                "❌ No products found for the search term 'Tshirt'!");

        System.out.println("✅ Verified Product Search: Search results are visible for 'Tshirt'");
    }
}
