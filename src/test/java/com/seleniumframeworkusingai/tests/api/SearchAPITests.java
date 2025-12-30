package com.seleniumframeworkusingai.tests.api;

import com.seleniumframeworkusingai.apis.SearchAPI;
import com.seleniumframeworkusingai.utils.JsonHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SearchAPITests {

    private final SearchAPI searchAPI = new SearchAPI();

    @Test(description = "Verify POST /api/searchProduct returns 200 with valid search term")
    public void verifySearchProductValidTerm() {
        // 🔹 Step 1: Perform API call
        Response response = searchAPI.searchProduct("top");

        // 🔹 Step 2: Expect HTTP 200
        Assert.assertEquals(response.statusCode(), 200, "❌ Expected status code 200 for valid search!");
        System.out.println("🔍 Raw Response Body:");
        System.out.println(response.getBody().asString());

        // 🔹 Step 3: Clean HTML wrappers and parse JSON
        JsonPath json = JsonHelper.parseHtmlWrappedJson(response.getBody().asString());

        // 🔹 Step 4: Validate response code and products
        int bodyResponseCode = json.getInt("responseCode");
        Assert.assertEquals(bodyResponseCode, 200, "❌ API responseCode field is not 200!");

        List<Map<String, Object>> products = json.getList("products");
        Assert.assertNotNull(products, "❌ 'products' list is null in response!");
        Assert.assertTrue(products.size() > 0, "❌ No products found for valid search term!");

        System.out.println("✅ Search API returned " + products.size() + " products for valid search term.");
    }


    @Test(description = "Verify POST /api/searchProduct returns error for missing search term")
    public void verifySearchProductMissingTerm() {
        // Step 1: Perform API call without search term
        Response response = searchAPI.searchProductMissingTerm();

        System.out.println("🔍 Actual Status Code: " + response.statusCode());
        System.out.println("🔍 Raw Response: " + response.getBody().asString());

        // Step 2: Expect HTTP 200 (this API always returns 200)
        Assert.assertEquals(response.statusCode(), 200, "❌ Expected HTTP 200 from API!");

        // Step 3: Clean HTML wrappers (remove <html>, <body>)
        String rawBody = response.getBody().asString()
                .replaceAll("(?s)<[^>]*>", "")  // removes all HTML tags
                .trim();

        // Step 4: Parse JSON
        io.restassured.path.json.JsonPath json = new io.restassured.path.json.JsonPath(rawBody);

        int bodyResponseCode = json.getInt("responseCode");
        String message = json.getString("message");

        // Step 5: Assert internal responseCode == 400
        Assert.assertEquals(bodyResponseCode, 400,
                "❌ Expected API responseCode 400 for missing term but got: " + bodyResponseCode);

        System.out.println("✅ Search API handled missing term correctly. Message: " + message);
    }

}
