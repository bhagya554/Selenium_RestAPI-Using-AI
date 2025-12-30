package com.seleniumframeworkusingai.tests.base;

import com.seleniumframeworkusingai.base.DriverFactory;
import com.seleniumframeworkusingai.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * BaseTest
 * ----------
 * Common setup and teardown logic for all UI and API test classes.
 *
 * - Initializes and quits driver per test (thread-safe)
 * - Optionally navigates to base URL
 * - Works seamlessly with Selenium 4.36.0 (Selenium Manager)
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;

    /**
     * Runs before each test method.
     * Initializes WebDriver and optionally navigates to base URL.
     *
     * @param navigateToBaseUrl optional parameter; defaults to true.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"navigateToBaseUrl"})
    public void setUp(@Optional("true") String navigateToBaseUrl) {
        // Initialize thread-safe WebDriver instance
        driver = DriverFactory.getDriver();

        // Load base URL from config file
        baseUrl = ConfigReader.get("base.url");

        // Optional: navigate automatically to base URL
        if (Boolean.parseBoolean(navigateToBaseUrl)) {
            driver.get(baseUrl);
        }

        // Optional: maximize browser window
        driver.manage().window().maximize();

        System.out.println("✅ [Setup] WebDriver initialized on thread: " + Thread.currentThread().getName());
    }

    /**
     * Runs after each test method.
     * Quits WebDriver safely to free resources.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
            System.out.println("🧹 [Teardown] WebDriver quit on thread: " + Thread.currentThread().getName());
        }
    }

    /**
     * Optional: suite-level setup logic (runs once before suite)
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("🏗 [Suite Start] Selenium_Framework_Using_AI Test Suite started.");
    }

    /**
     * Optional: suite-level teardown logic (runs once after suite)
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("🏁 [Suite End] Selenium_Framework_Using_AI Test Suite completed.");
    }
}
