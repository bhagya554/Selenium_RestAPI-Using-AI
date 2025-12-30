package com.seleniumframeworkusingai.tests.ui;

import com.seleniumframeworkusingai.tests.base.BaseTest;
import com.seleniumframeworkusingai.pages.ContactUsPage;
import com.seleniumframeworkusingai.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactUsPageTests extends BaseTest {

    @Test(description = "Verify user can submit contact form successfully")
    public void verifyContactFormSubmission() {
        HomePage home = new HomePage();
        ContactUsPage contactUs = home.goToContactUsPage();

        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/test.txt";
        contactUs.fillContactForm("John", "john@example.com", "Test", "This is a test message", filePath)
                .submitForm();

        Assert.assertTrue(contactUs.isSuccessMessageDisplayed(),
                "Contact form success message not displayed!");
    }
}
