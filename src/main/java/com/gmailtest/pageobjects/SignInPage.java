package com.gmailtest.pageobjects;

import com.gmailtest.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    public void fillPassword(WebDriver driver, String s) {
        WebUtil.waitForElementVisible(driver, By.id("Passwd"));
        WebUtil.sendKeys(driver, By.id("Passwd"), s);
    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebUtil.click(driver, By.id("signIn"));
        WebUtil.waitForElementVisible(driver, By.partialLinkText("Inbox"));

        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean isSignInButtonExist(WebDriver driver) {
        return WebUtil.isElementExist(driver, By.id("signIn"));
    }

}
