package com.gmailtest.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
