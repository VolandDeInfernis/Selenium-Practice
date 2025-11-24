package org.SeleniumPractice.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestDataProvider {

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] provideLoginData(Method method) {
        String validUsername = ConfigReader.getProperty("valid.username");
        String validPassword = ConfigReader.getProperty("valid.password");
        String invalidUsername = ConfigReader.getProperty("invalid.username");
        String invalidPassword = ConfigReader.getProperty("invalid.password");

        return new Object[][] {
                {validUsername, validPassword, "success"},
                {invalidUsername, validPassword, "failure"},
                {validUsername, invalidPassword, "failure"}
        };
    }

    @DataProvider(name = "issueData")
    public Object[][] provideIssueData() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return new Object[][] {
                {"Test Issue " + timestamp, "Test description " + timestamp}
        };
    }
}