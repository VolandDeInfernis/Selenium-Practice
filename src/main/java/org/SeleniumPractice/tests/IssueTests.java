package org.SeleniumPractice.tests;

import org.testng.annotations.Test;
import org.SeleniumPractice.pages.DashboardPage;
import org.SeleniumPractice.pages.CreateIssuePage;
import org.SeleniumPractice.pages.IssuesPage;
import org.SeleniumPractice.pages.LoginPage;
import org.SeleniumPractice.utils.TestBase;
import org.SeleniumPractice.utils.SoftAssertHelper;
import org.SeleniumPractice.utils.TestDataProvider;
import org.SeleniumPractice.utils.ConfigReader;

public class IssueTests extends TestBase {

    @Test(dataProvider = "issueData", dataProviderClass = TestDataProvider.class)
    public void testIssueCreationWorkflow(String summary, String description) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickIssuesLink();

        IssuesPage issuesPage = new IssuesPage();
        issuesPage.clickCreateIssue();

        CreateIssuePage createIssuePage = new CreateIssuePage();
        createIssuePage.createIssue(summary, description);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        IssuesPage issuesPageAfterCreation = new IssuesPage();
        issuesPageAfterCreation.deleteCurrentIssue();

        SoftAssertHelper.assertAll();
    }
}