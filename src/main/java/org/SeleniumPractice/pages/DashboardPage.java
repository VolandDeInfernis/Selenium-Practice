package org.SeleniumPractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "a[data-test='ring-link issues-button']")
    private WebElement issuesLink;

    @FindBy(css = "a[data-test='ring-link dashboard-button']")
    private WebElement dashboardLink;

    @FindBy(css = "a[data-test='ring-link agile-boards-button']")
    private WebElement agileBoardsLink;

    @FindBy(css = "a[data-test='ring-link reports-button']")
    private WebElement reportsLink;

    @FindBy(css = "a[data-test='ring-link projects-button']")
    private WebElement projectsLink;

    @FindBy(css = "a[data-test='ring-link knowledge-base-button']")
    private WebElement knowledgeBaseLink;

    @FindBy(css = "a[data-test='ring-link gantt-button']")
    private WebElement ganttChartsLink;

    @FindBy(css = "[data-test='ring-dropdown ring-profile']")
    private WebElement userMenu;

    @FindBy(css = "[data-test='dashboard-title']")
    private WebElement dashboardTitle;

    private String previousUrl;

    public DashboardPage() {
        super();
    }

    public void clickIssuesLink() {
        waitForDashboardToLoad();
        saveCurrentUrl();
        clickElement(issuesLink);
    }

    public void clickDashboardLink() {
        saveCurrentUrl();
        clickElement(dashboardLink);
    }

    public void clickAgileBoardsLink() {
        saveCurrentUrl();
        clickElement(agileBoardsLink);
    }

    public void clickReportsLink() {
        saveCurrentUrl();
        clickElement(reportsLink);
    }

    public void clickProjectsLink() {
        saveCurrentUrl();
        clickElement(projectsLink);
    }

    public void clickKnowledgeBaseLink() {
        saveCurrentUrl();
        clickElement(knowledgeBaseLink);
    }

    public void clickGanttChartsLink() {
        saveCurrentUrl();
        clickElement(ganttChartsLink);
    }

    private void saveCurrentUrl() {
        this.previousUrl = getDriver().getCurrentUrl();
        System.out.println("Saved previous URL: " + previousUrl);
    }

    public void verifyNavigationSuccess(String expectedPageName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String currentUrl = getDriver().getCurrentUrl();

        softAssertTrue(!currentUrl.equals(previousUrl),
                "Should navigate away from previous page. Previous: " + previousUrl + ", Current: " + currentUrl);

        softAssertTrue(currentUrl.toLowerCase().contains(expectedPageName.toLowerCase()),
                "Should navigate to: " + expectedPageName + ". Current URL: " + currentUrl);

        System.out.println("Successfully navigated from " + previousUrl + " to: " + currentUrl);
    }

    private void waitForDashboardToLoad() {
        try {
            int attempts = 0;
            while (attempts < 10) {
                if (dashboardTitle.isDisplayed()) {
                    System.out.println("Dashboard loaded successfully");
                    Thread.sleep(2000);
                    return;
                }
                Thread.sleep(1000);
                attempts++;
            }
            System.out.println("Dashboard not loaded after 10 seconds");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void openUserMenu() {
        clickElement(userMenu);
    }
}