package com.gmailtest.pageobjects;

import com.gmailtest.util.WebUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class EmailHomePage {

    public SignInPage signOut(WebDriver driver) throws InterruptedException {
        //Profile button
        WebUtil.click(driver, By.cssSelector("span[class='gb_Ka gbii']"));
        //SignOut button
        WebUtil.click(driver, By.id("gb_71"));

        WebUtil.handleAlert(driver);

        WebUtil.waitForElementVisible(driver, By.id("signIn"));
        return PageFactory.initElements(driver, SignInPage.class);
    }

    public boolean isInboxExist(WebDriver driver) {
        return WebUtil.isElementExist(driver, By.partialLinkText("Inbox"));
    }

    public void clickComposeButton(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public void fillingRecipient(WebDriver driver, String s) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("textarea[name='to']"));
        WebUtil.sendKeys(driver, By.cssSelector("textarea[name='to']"), s);
    }

    public void fillingSubject(WebDriver driver, String subjectText) {
        WebUtil.sendKeys(driver, By.cssSelector("input[name='subjectbox']"), subjectText);
    }

    public void fillingBody(WebDriver driver, String bodyText) {
        WebUtil.sendKeys(driver, By.cssSelector("div[aria-label='Message Body']"), bodyText);
    }

    public void clickSendEmail(WebDriver driver) {
        WebUtil.waitForTextFilled(driver, By.cssSelector("div[aria-label='Message Body']")); //wait until body text added
        WebUtil.click(driver, By.cssSelector("div[aria-label*='Send']"));
    }

    public void clickInboxWithNewEmail(WebDriver driver, String s) {
        WebUtil.waitForElementVisible(driver, By.linkText(s));
        WebUtil.click(driver, By.linkText(s));
    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("div[class='y6'] span[id] b"));
        WebUtil.click(driver, By.cssSelector("div[class='y6'] span[id] b"));

        return PageFactory.initElements(driver, EmailViewPage.class);
    }

    public void waitScreenUpdate(int i) throws InterruptedException {
        WebUtil.threadWait(i);
    }
}
