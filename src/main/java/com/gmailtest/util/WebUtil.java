package com.gmailtest.util;

import com.gmailtest.pageobjects.AccountPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtil {
    final static int MAX_TIME_OUT = 30;

    public static AccountPage goToAccountPage(WebDriver driver) {
        driver.get("http://gmail.com");
        return PageFactory.initElements(driver, AccountPage.class);
    }

    public static void click(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, MAX_TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean isElementExist(WebDriver driver, By by) {
        return driver.findElements(by).size() > 0;
    }

    public static void sendKeys(WebDriver driver, By by, String s) {
        WebElement recipientTextArea = driver.findElement(by);
        recipientTextArea.clear();
        recipientTextArea.sendKeys(s);
    }

    public static void handleAlert(WebDriver driver) throws InterruptedException {
        try {
            threadWait(1000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert or alert error");
        }
    }

    public static String getElementText(WebDriver driver, By by) {
        WebElement stringElement = driver.findElement(by);
        return stringElement.getText();
    }

    public static void threadWait(int i) throws InterruptedException {
        Thread.sleep(i);
    }
}
