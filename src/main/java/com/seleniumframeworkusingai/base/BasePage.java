package com.seleniumframeworkusingai.base;

import com.seleniumframeworkusingai.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage class following Selenium 4+ best practices.
 * <p>
 * - Provides shared WebDriver instance (thread-safe via DriverFactory)
 * - Initializes PageFactory elements
 * - Provides reusable helper/wait utilities
 * - Enforces clean Page Object Model (POM) design
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor initializes WebDriver and PageFactory elements.
     */
    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        int explicitWait = ConfigReader.getInt("explicit.wait");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }

    // -----------------------------------------------------------------------
    // ✅ Common Navigation Utilities
    // -----------------------------------------------------------------------

    /**
     * Opens the specified URL in the current browser.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Gets the title of the current page.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Returns the current page URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // -----------------------------------------------------------------------
    // ✅ Wait Utilities (Modern WebDriverWait Usage)
    // -----------------------------------------------------------------------

    /**
     * Waits until the specified element is visible.
     */
    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the specified element is clickable.
     */
    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until a specific locator is visible.
     */
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the page's ready state is 'complete'.
     */
    protected void waitForPageToLoadCompletely() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    // -----------------------------------------------------------------------
    // ✅ Common Action Utilities
    // -----------------------------------------------------------------------

    /**
     * Clicks an element safely after waiting for it to be clickable.
     */
    protected void click(WebElement element) {
        waitForClickable(element).click();
    }

    /**
     * Sends text to an input field after waiting for visibility.
     */
    protected void type(WebElement element, String text) {
        WebElement visibleElement = waitForVisibility(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    /**
     * Scrolls element into view using JavaScript.
     */
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Checks if element is displayed safely (without throwing).
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    // -----------------------------------------------------------------------
    // ✅ Browser/Driver Lifecycle
    // -----------------------------------------------------------------------

    /**
     * Maximizes the current browser window.
     */
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Closes the current browser tab.
     */
    public void closeBrowser() {
        driver.close();
    }

    /**
     * Quits the entire WebDriver session.
     */
    public void quitDriver() {
        DriverFactory.quitDriver();
    }
}
