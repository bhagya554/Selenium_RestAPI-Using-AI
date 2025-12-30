package com.seleniumframeworkusingai.base;

import com.seleniumframeworkusingai.enums.BrowserType;
import com.seleniumframeworkusingai.utils.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent instantiation
    }

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Returns the thread-safe WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            tlDriver.set(createDriver());
        }
        return tlDriver.get();
    }

    /**
     * Creates the WebDriver instance using Selenium Manager (no WebDriverManager needed).
     */
    private static WebDriver createDriver() {
        BrowserType browser = BrowserType.valueOf(ConfigReader.get("browser").toUpperCase());
        boolean headless = ConfigReader.getBoolean("headless");
        int width = ConfigReader.getInt("window.width");
        int height = ConfigReader.getInt("window.height");
        int implicitWait = ConfigReader.getInt("implicit.wait");
        int pageLoadTimeout = ConfigReader.getInt("page.load.timeout");

        WebDriver driver;

        switch (browser) {
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (headless) {
                    options.addArguments("--headless=new");
                }
                driver = new FirefoxDriver(options);
            }

            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (headless) {
                    options.addArguments("--headless=new");
                }
                driver = new EdgeDriver(options);
            }

            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (headless) {
                    options.addArguments("--headless=new", "--disable-gpu");
                }
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options); // ✅ Selenium Manager auto-handles the driver
            }

            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Configure driver defaults
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().window().setSize(new Dimension(width, height));

        return driver;
    }

    /**
     * Quits and removes the driver instance.
     */
    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
