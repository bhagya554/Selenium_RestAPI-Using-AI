package com.seleniumframeworkusingai.tests.api;

import com.seleniumframeworkusingai.apis.ProductsAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsAPITests {

    private final ProductsAPI productsAPI = new ProductsAPI();

    @Test(description = "Verify GET /api/productsList returns 200 and contains product data")
    public void verifyGetAllProducts() {
        Response response = productsAPI.getAllProducts();

        Assert.assertEquals(response.statusCode(), 200, "❌ Expected status code 200 for GET productsList!");
        Assert.assertTrue(response.jsonPath().getList("products").size() > 0,
                "❌ No products found in GET /api/productsList response!");

        System.out.println("✅ GET /api/productsList returned products successfully.");
    }

    @Test(description = "Verify POST /api/productsList is not allowed (returns 405)")
    public void verifyPostProductsListNotAllowed() {
        Response response = productsAPI.attemptPostProductsList(null);
        Assert.assertEquals(response.statusCode(), 405,
                "❌ Expected status code 405 for POST /api/productsList!");
        System.out.println("✅ POST /api/productsList returned 405 as expected.");
    }
}
