package com.seleniumframeworkusingai.tests.ui;


import com.seleniumframeworkusingai.pages.HomePage;
import com.seleniumframeworkusingai.pages.ProductsPage;
import com.seleniumframeworkusingai.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    @Test(description = "Verify user is able to load the home page successfully")
    public void verifyHomePageTitle() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isAtHomePage(),
                "Home page did not load correctly!");
    }

    @Test(description = "Verify navigation from Home Page to Products Page")
    public void verifyNavigationToProductsPage() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = homePage.goToProductsPage();
        Assert.assertTrue(productsPage.isProductsListVisible(),
                "Products list not visible on Products page!");
    }
}
