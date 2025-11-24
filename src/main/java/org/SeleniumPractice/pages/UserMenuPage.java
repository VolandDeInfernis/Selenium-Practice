package org.SeleniumPractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMenuPage extends BasePage {

    @FindBy(xpath = "//button[text()='Выйти']")
    private WebElement logoutButton;

    public UserMenuPage() {
        super();
    }

    public void clickLogout() {
        clickElement(logoutButton);
    }
}