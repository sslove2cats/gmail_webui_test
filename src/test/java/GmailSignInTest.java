import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by suejlee on 10/17(Saturday).
 */
public class GmailSignInTest {
    WebDriver driver = new FirefoxDriver();

    @Test
    public void gmailLogInShouldBeSuccessful(){

        WebDriverWait wait = new WebDriverWait(driver, 30);

        //1. Go to Gmail website
        driver.get("http://gmail.com");

        //2. Fill in username
        WebElement usernameTextBox = driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys("sslove2cats@gmail.com");

        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        //3. Fill in password and SignIn
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("sslove2cats1");

        //4. Click sign In
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();

        //5. Verify Inbox exists
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exists", driver.findElements(By.partialLinkText("Inbox")).size() > 0);

        //6. Click user profile button
        WebElement profileButton = driver.findElement(By.cssSelector("span[class='gb_Ka gbii']"));
        profileButton.click();

        //7. Click Sign out button
        WebElement signOutButton = driver.findElement(By.id("gb_71"));
        signOutButton.click();
//        driver.switchTo().alert().dismiss();

        //8. Verify user did sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));
        Assert.assertTrue("Sign In page should show", driver.findElements(By.id("signIn")).size()>0);

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
