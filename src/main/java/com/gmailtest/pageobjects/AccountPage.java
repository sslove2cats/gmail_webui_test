package com.gmailtest.pageobjects;

import com.gmailtest.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    public void fillInUsername(WebDriver driver, String s) {
        WebUtil.sendKeys(driver, By.id("Email"), s);
    }

    public SignInPage goToPasswordPage(WebDriver driver) {
        WebUtil.click(driver, By.id("next"));
        return PageFactory.initElements(driver, SignInPage.class);
    }
}
