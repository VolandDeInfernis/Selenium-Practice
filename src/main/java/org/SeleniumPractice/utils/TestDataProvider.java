package org.SeleniumPractice.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestDataProvider {

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] provideLoginData(Method method) {
        return new Object[][] {
                {"QA2", "TestPassword1", "success"},
                {"invalid-user", "TestPassword1", "failure"},
                {"QA2","invalid-password", "failure"}
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