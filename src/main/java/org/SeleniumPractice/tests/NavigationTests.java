package org.SeleniumPractice.tests;

import org.testng.annotations.Test;
import org.SeleniumPractice.pages.DashboardPage;
import org.SeleniumPractice.pages.LoginPage;
import org.SeleniumPractice.utils.TestBase;
import org.SeleniumPractice.utils.SoftAssertHelper;
import org.SeleniumPractice.utils.ConfigReader;

public class NavigationTests extends TestBase {

    @Test
    public void testAllNavigationLinks() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        DashboardPage dashboardPage = new DashboardPage();

        dashboardPage.clickIssuesLink();
        dashboardPage.verifyNavigationSuccess("issues");

        dashboardPage.clickDashboardLink();
        dashboardPage.verifyNavigationSuccess("dashboard");

        dashboardPage.clickAgileBoardsLink();
        dashboardPage.verifyNavigationSuccess("agile");

        dashboardPage.clickReportsLink();
        dashboardPage.verifyNavigationSuccess("reports");

        dashboardPage.clickProjectsLink();
        dashboardPage.verifyNavigationSuccess("projects");

        dashboardPage.clickKnowledgeBaseLink();
        dashboardPage.verifyNavigationSuccess("articles");

        dashboardPage.clickGanttChartsLink();
        dashboardPage.verifyNavigationSuccess("gantt");

        SoftAssertHelper.assertAll();
    }
}