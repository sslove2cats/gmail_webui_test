package com.gmailtest.util;

import com.gmailtest.pageobjects.AccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebUtil {
    public static AccountPage goToAccountPage(WebDriver driver) {
        driver.get("http://gmail.com");
        return PageFactory.initElements(driver, AccountPage.class);
    }
}
