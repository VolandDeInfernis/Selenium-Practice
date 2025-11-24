package org.SeleniumPractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuesPage extends BasePage {

    @FindBy(css = "a[data-test='createIssueButton']")
    private WebElement createIssueButton;

    @FindBy(css = "[data-test='ring-list-item-label'] .dangerText__b026")
    private WebElement deleteIssueButton;

    @FindBy(css = "button[data-test='confirm-ok-button']")
    private WebElement confirmDeleteButton;

    public IssuesPage() {
        super();
    }

    public void clickCreateIssue() {
        clickElement(createIssueButton);
    }

    public void deleteCurrentIssue() {
        WebElement moreButton = findMoreButtonInSummaryToolbar();
        clickWithJavaScript(moreButton);
        clickElement(deleteIssueButton);
        clickElement(confirmDeleteButton);
    }

    private WebElement findMoreButtonInSummaryToolbar() {
        return getDriver().findElement(By.cssSelector(".summaryToolbar__c231 button[aria-label='Показать больше']"));
    }
}