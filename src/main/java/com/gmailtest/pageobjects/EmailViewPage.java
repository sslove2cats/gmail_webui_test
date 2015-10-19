package com.gmailtest.pageobjects;

import com.gmailtest.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailViewPage {

    public String getEmailSubjectText(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("h2[class='hP']"));
        return WebUtil.getElementText(driver, By.cssSelector("h2[class='hP']"));
    }

    public String getEmailBodyText(WebDriver driver) {
        return WebUtil.getElementText(driver, By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
    }
}
