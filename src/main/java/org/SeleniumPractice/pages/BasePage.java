package org.SeleniumPractice.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.SeleniumPractice.utils.SoftAssertHelper;
import org.SeleniumPractice.utils.TestBase;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SoftAssert softAssert;

    public BasePage() {
        this.driver = new TestBase().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.softAssert = SoftAssertHelper.getSoftAssert();
        waitForPageToLoad();
        PageFactory.initElements(driver, this);
    }

    protected void waitForPageToLoad() {
        try {
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            wait.until(webDriver ->
                    js.executeScript("return document.readyState").equals("complete"));

        } catch (Exception e) {
            System.out.println("Page load wait completed with exception: " + e.getMessage());
        }
    }

    protected void waitAndClick(WebElement element) {
        waitForPageToLoad();
        clickElement(element);
    }

    protected void waitAndEnterText(WebElement element, String text) {
        waitForPageToLoad();
        enterText(element, text);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
        } catch (Exception e) {
            softAssert.fail("Failed to click element: " + e.getMessage());
        }
    }

    protected void enterText(WebElement element, String text) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            softAssert.fail("Failed to enter text in element: " + e.getMessage());
        }
    }

    public void softAssertTrue(boolean condition, String message) {
        softAssert.assertTrue(condition, message);
    }

    public void softAssertFalse(boolean condition, String message) {
        softAssert.assertFalse(condition, message);
    }

    public void softAssertEquals(Object actual, Object expected, String message) {
        softAssert.assertEquals(actual, expected, message);
    }

    public void softAssertElementDisplayedSafely(WebElement element, String elementName) {
        try {
            boolean isDisplayed = element.isDisplayed();
            softAssert.assertTrue(isDisplayed, elementName + " should be displayed");
        } catch (Exception e) {
            softAssert.fail(elementName + " is not present or not displayed: " + e.getMessage());
        }
    }

    protected void clickWithJavaScript(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            softAssert.fail("Failed to click with JavaScript: " + e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}