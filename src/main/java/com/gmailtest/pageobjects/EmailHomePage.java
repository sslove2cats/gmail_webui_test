package com.gmailtest.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.jvm.hotspot.debugger.Page;

public class EmailHomePage {

    public SignInPage signOut(WebDriver driver) throws InterruptedException {
        WebElement profileButton = driver.findElement(By.cssSelector("span[class='gb_Ka gbii']"));
        profileButton.click();

        WebElement signOutButton = driver.findElement(By.id("gb_71"));
        signOutButton.click();

        try {
            Thread.sleep(1000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert or alert error");
        }

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));

        return PageFactory.initElements(driver, SignInPage.class);
    }

    public boolean isInboxExist(WebDriver driver) {
        return driver.findElements(By.partialLinkText("Inbox")).size() > 0;
    }

    public void clickComposeButton(WebDriver driver) {
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
    }

    public void fillingRecipient(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        WebElement recipientTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        recipientTextArea.clear();
        recipientTextArea.sendKeys(s);
    }

    public void fillingSubject(WebDriver driver, String subjectText) {
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subjectText);
    }

    public void fillingBody(WebDriver driver, String bodyText) {
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        bodyTextArea.clear();
        bodyTextArea.sendKeys(bodyText);
    }

    public void clickSendEmail(WebDriver driver) {
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*='Send']"));
        sendButton.click();
    }

    public void clickInboxWithNewEmail(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(s)));
        WebElement inboxLinkage = driver.findElement(By.linkText(s));
        inboxLinkage.click();

    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();

        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
