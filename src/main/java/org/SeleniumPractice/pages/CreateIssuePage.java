package org.SeleniumPractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuePage extends BasePage {

    @FindBy(css = "textarea[data-test='summary']")
    private WebElement summaryInput;

    @FindBy(css = "button[data-test='submit-button']")
    private WebElement createButton;

    public CreateIssuePage() {
        super();
    }

    public void createIssue(String summary, String description) {
        waitAndEnterText(summaryInput, summary);
        waitAndClick(createButton);
        waitForPageToLoad();
    }
}