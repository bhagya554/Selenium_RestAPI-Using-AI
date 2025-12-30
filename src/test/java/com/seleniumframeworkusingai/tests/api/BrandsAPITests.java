package com.seleniumframeworkusingai.tests.api;
import com.seleniumframeworkusingai.apis.BrandsAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrandsAPITests {

    private final BrandsAPI brandsAPI = new BrandsAPI();

    @Test(description = "Verify GET /api/brandsList returns 200 and contains brands")
    public void verifyGetAllBrands() {
        Response response = brandsAPI.getAllBrands();

        Assert.assertEquals(response.statusCode(), 200, "❌ Expected status code 200 for GET brandsList!");
        Assert.assertTrue(response.jsonPath().getList("brands").size() > 0,
                "❌ No brands found in GET /api/brandsList response!");

        System.out.println("✅ GET /api/brandsList returned brand data successfully.");
    }

    @Test(description = "Verify PUT /api/brandsList returns 405 (method not allowed)")
    public void verifyPutBrandsListNotAllowed() {
        Response response = brandsAPI.attemptPutBrandsList(null);

        Assert.assertEquals(response.statusCode(), 405,
                "❌ Expected status code 405 for PUT /api/brandsList!");
        System.out.println("✅ PUT /api/brandsList returned 405 as expected.");
    }
}
