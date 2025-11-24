package org.SeleniumPractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button.auth-button")
    private WebElement loginButton;

    @FindBy(id = "rg-checkbox-0")
    private WebElement rememberMeCheckbox;

    @FindBy(css = "input")
    private List<WebElement> inputFields;

    @FindBy(css = "button")
    private List<WebElement> buttons;

    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLogin() {
        clickElement(loginButton);
    }

    public void login(String username, String password) {
        waitAndEnterText(usernameField, username);
        waitAndEnterText(passwordField, password);
        waitAndClick(loginButton);
    }

    public boolean isLoginPageDisplayed() {
        try {
            return usernameField.isDisplayed() &&
                    passwordField.isDisplayed() &&
                    loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyBasicLoginElements() {
        System.out.println("Verifying Basic Login Elements");

        softAssertElementDisplayedSafely(usernameField, "Username field");
        softAssertElementDisplayedSafely(passwordField, "Password field");
        softAssertElementDisplayedSafely(loginButton, "Login button");

        System.out.println("Basic elements verification completed");
    }

    public void verifyLoginFunctionality(String username, String password) {
        System.out.println("Testing Login: " + username);

        enterUsername(username);
        enterPassword(password);

        softAssertEquals(usernameField.getAttribute("value"), username, "Username field should be filled");
        softAssertEquals(passwordField.getAttribute("value"), password, "Password field should be filled");

        clickLogin();

        System.out.println("Login attempt completed");
    }

    public void verifySuccessfulLogin() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after login: " + currentUrl);
        boolean isStillOnLoginPage = isLoginPageDisplayed();

        softAssertFalse(isStillOnLoginPage,
                "Should be redirected from login page. Current URL: " + currentUrl);
    }

    public void verifyFailedLogin() {
        softAssertTrue(isLoginPageDisplayed(), "Should remain on login page after failed login");
    }
}