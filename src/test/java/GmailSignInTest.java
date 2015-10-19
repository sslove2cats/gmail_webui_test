import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GmailSignInTest {
    WebDriver driver = new FirefoxDriver();

    @Test
    public void gmailLogInShouldBeSuccessful() throws InterruptedException{

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

        try {
            System.out.println("Alert handling here");
            Thread.sleep(1000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert or alert error");
        }

        //8. Verify user did sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));
        Assert.assertTrue("Sign In page should show", driver.findElements(By.id("signIn")).size()>0);
    }

    @Test
    public void gmailSendAndReceiveEmailTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        //1. Sign in
        //1.1. Go to Gmail website
        driver.get("http://gmail.com");
        //1.2. Fill in username
        WebElement usernameTextBox = driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys("sslove2cats@gmail.com");
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();
        //1.3. Fill in password and SignIn
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("sslove2cats1");
        //1.4. Click sign In
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        //1.5. Verify Inbox exists
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exists", driver.findElements(By.partialLinkText("Inbox")).size() > 0);

        Thread.sleep(5000);

        //2. Click Compose
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();

        //3. Fill in recipient
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        WebElement recipientTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        recipientTextArea.clear();
        recipientTextArea.sendKeys("sslove2cats@gmail.com");

        //4. Fill in subject
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectTextArea.clear();
        final String subjectText = "Gmail Send Email Test";
        subjectTextArea.sendKeys(subjectText);

        //5. Fill in email body
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        bodyTextArea.clear();
        final String bodyText = "Gmail Send Email Body Test";
        bodyTextArea.sendKeys(bodyText);

        //6. Click Send
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*='Send']"));
        sendButton.click();

        //7. Click Inbox again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inbox (1)")));
        WebElement inboxLinkage = driver.findElement(By.linkText("Inbox (1)"));
        inboxLinkage.click();

        //8. Click email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();

        //9. Verify the email subject and email body is correct
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[class='hP']")));
        WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
        Assert.assertEquals("Email subject should be the same", subjectText, subjectArea.getText());
        WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
        Assert.assertEquals("Email Body should be the same", bodyText, bodyArea.getText());

        //10. Sign out
        WebElement profileButton = driver.findElement(By.cssSelector("span[class='gb_Ka gbii']"));
        profileButton.click();
        WebElement signOutButton = driver.findElement(By.id("gb_71"));
        signOutButton.click();

        try {
            System.out.println("Alert handling here");
            Thread.sleep(1000);
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert or alert error");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));
        Assert.assertTrue("Sign In page should show", driver.findElements(By.id("signIn")).size()>0);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
