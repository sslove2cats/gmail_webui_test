package com.gmailtest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    public void fillInUsername(WebDriver driver, String s) {
        WebElement usernameTextBox = driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys(s);
    }

    public SignInPage goToPasswordPage(WebDriver driver) {
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();
        return PageFactory.initElements(driver, SignInPage.class);
    }
}
