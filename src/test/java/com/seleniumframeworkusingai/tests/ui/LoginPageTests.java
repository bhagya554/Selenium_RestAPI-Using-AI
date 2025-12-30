package com.seleniumframeworkusingai.tests.ui;
import com.seleniumframeworkusingai.tests.base.BaseTest;
import com.seleniumframeworkusingai.pages.HomePage;
import com.seleniumframeworkusingai.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTest {

    @Test(description = "Verify user cannot login with invalid credentials")
    public void verifyInvalidLogin() {
        HomePage home = new HomePage();
        LoginPage loginPage = home.goToLoginPage();
        loginPage.login("fakeuser@test.com", "wrongpassword");
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
                "Expected error message not displayed!");
    }
}
