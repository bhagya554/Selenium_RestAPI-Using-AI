package com.seleniumframeworkusingai.tests.api;

import com.seleniumframeworkusingai.apis.AuthAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthAPITests {

    private final AuthAPI authAPI = new AuthAPI();

    @Test(description = "Verify POST /api/verifyLogin returns 200 for valid credentials")
    public void verifyValidLogin() {
        // ⚠️ Replace with valid credentials created on automationexercise.com
        String validEmail = "validuser@test.com";
        String validPassword = "validpassword";

        Response response = authAPI.verifyLogin(validEmail, validPassword);

        Assert.assertEquals(response.statusCode(), 200, "❌ Expected 200 for valid login credentials!");
        Assert.assertTrue(response.asString().contains("User exists!"), "❌ Expected 'User exists!' in response!");
        System.out.println("✅ Valid credentials verified successfully.");
    }

    @Test(description = "Verify POST /api/verifyLogin returns 404 for invalid credentials")
    public void verifyInvalidLogin() {
        Response response = authAPI.verifyLogin("fakeuser@test.com", "wrongpassword");

        Assert.assertEquals(response.statusCode(), 404, "❌ Expected 404 for invalid credentials!");
        Assert.assertTrue(response.asString().contains("User not found!"),
                "❌ Expected 'User not found!' message!");
        System.out.println("✅ Invalid login correctly returned 404.");
    }
}
