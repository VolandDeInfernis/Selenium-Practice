package org.SeleniumPractice.tests;

import org.testng.annotations.Test;
import org.SeleniumPractice.pages.DashboardPage;
import org.SeleniumPractice.pages.LoginPage;
import org.SeleniumPractice.pages.UserMenuPage;
import org.SeleniumPractice.utils.TestBase;
import org.SeleniumPractice.utils.SoftAssertHelper;
import org.SeleniumPractice.utils.ConfigReader;

public class LogoutTests extends TestBase {

    @Test
    public void testUserLogout() {
        System.out.println("Testing user logout");

        LoginPage loginPage = new LoginPage();
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.openUserMenu();

        UserMenuPage userMenuPage = new UserMenuPage();
        userMenuPage.clickLogout();

        LoginPage newLoginPage = new LoginPage();
        newLoginPage.softAssertTrue(newLoginPage.isLoginPageDisplayed(),
                "Should be redirected to login page after logout");

        SoftAssertHelper.assertAll();
    }
}