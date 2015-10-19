package com.gmailtest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailViewPage {

    public String getEmailSubjectText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[class='hP']")));
        WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
        return subjectArea.getText();
    }

    public String getEmailBodyText(WebDriver driver) {
        WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
        return bodyArea.getText();
    }
}
