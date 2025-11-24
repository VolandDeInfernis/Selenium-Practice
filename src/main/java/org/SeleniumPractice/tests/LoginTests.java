package org.SeleniumPractice.tests;

import org.testng.annotations.Test;
import org.SeleniumPractice.pages.LoginPage;
import org.SeleniumPractice.utils.TestBase;
import org.SeleniumPractice.utils.TestDataProvider;
import org.SeleniumPractice.utils.SoftAssertHelper;

public class LoginTests extends TestBase {

    @Test
    public void testBasicElements() {
        System.out.println("Thread " + Thread.currentThread().getId() + " - Testing Basic Elements");

        LoginPage loginPage = new LoginPage();

        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Thread " + Thread.currentThread().getId() + " - Current URL: " + currentUrl);

        loginPage.softAssertTrue(currentUrl.contains("193.233.193.42"),
                "Should be on correct URL before element testing");

        loginPage.verifyBasicLoginElements();

        SoftAssertHelper.assertAll();
        System.out.println("Thread " + Thread.currentThread().getId() + " - Basic Elements Test Completed");
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void testLoginFunctionality(String username, String password, String expectedResult) {
        System.out.println("Thread " + Thread.currentThread().getId() + " - Testing Login: " + username);

        String initialUrl = getDriver().getCurrentUrl();
        System.out.println("Thread " + Thread.currentThread().getId() + " - Initial URL: " + initialUrl);

        LoginPage loginPage = new LoginPage();

        loginPage.softAssertTrue(initialUrl.contains("193.233.193.42"),
                "Should start on test application URL");

        loginPage.verifyLoginFunctionality(username, password);

        if ("success".equals(expectedResult)) {
            loginPage.verifySuccessfulLogin();
        } else {
            loginPage.verifyFailedLogin();
        }

        SoftAssertHelper.assertAll();
        System.out.println("Thread " + Thread.currentThread().getId() + " - Login Test Completed");
    }
}