package com.gmailtest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    public void fillPassword(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys(s);
    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean isSignInButtonExist(WebDriver driver) {
        return driver.findElements(By.id("signIn")).size()>0;
    }

}
