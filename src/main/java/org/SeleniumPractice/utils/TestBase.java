package org.SeleniumPractice.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class TestBase {
    protected static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final String BASE_URL = "http://193.233.193.42:9091";
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    @BeforeMethod
    public void setUp() {
        System.out.println("Thread " + Thread.currentThread().getId() + " - Setting up Chrome driver");

        setupChromeDriver();
        getDriver().get(BASE_URL);

        SoftAssertHelper.reset();

        System.out.println("Thread " + Thread.currentThread().getId() + " - Driver setup completed");
    }

    private void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-default-apps");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driverThreadLocal.set(driver);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver driver = getDriver();

        try {
            SoftAssertHelper.assertAll();
        } catch (AssertionError e) {
            System.out.println("Thread " + Thread.currentThread().getId() + " - Soft assertions failed: " + e.getMessage());
            if (result.getStatus() == ITestResult.SUCCESS) {
                result.setStatus(ITestResult.FAILURE);
            }
        }

        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            takeScreenshot(result.getName() + "_thread_" + Thread.currentThread().getId());
        }

        if (driver != null) {
            System.out.println("Thread " + Thread.currentThread().getId() + " - Closing driver");
            driver.quit();
            driverThreadLocal.remove();
        }

        SoftAssertHelper.reset();
    }

    private void takeScreenshot(String testName) {
        try {
            WebDriver driver = getDriver();
            if (driver == null) return;

            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = SCREENSHOT_DIR + testName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(screenshot.toPath(), Paths.get(fileName));
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}