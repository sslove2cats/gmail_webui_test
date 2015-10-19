import com.gmailtest.pageobjects.EmailHomePage;
import com.gmailtest.pageobjects.SignInPage;
import com.gmailtest.pageobjects.AccountPage;
import com.gmailtest.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailSignInTest {
    WebDriver driver = new FirefoxDriver();

    @Test
    public void gmailLogInShouldBeSuccessful() throws InterruptedException{

        //1. Go to Gmail website
        AccountPage accountPage = WebUtil.goToAccountPage(driver);

        //2. Fill in username and go to SignIn page
        accountPage.fillInUsername(driver, "sslove2cats@gmail.com");
        SignInPage signInPage = accountPage.goToPasswordPage(driver);

        //3. Fill in password
        signInPage.fillPassword(driver, "sslove2cats1");

        //4. Click sign In
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        //5. Verify user did sign in
        Assert.assertTrue("Inbox should exists", emailHomePage.isInboxExist(driver));

        //6. Sign out
        signInPage = emailHomePage.signOut(driver);

        //7. Verify user did sign out
        Assert.assertTrue("Sign In page should show", signInPage.isSignInButtonExist(driver));
    }

    @Test
    public void gmailSendAndReceiveEmailTest() throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(driver, 30);

        //1.1 Go to Gmail website
        AccountPage accountPage = WebUtil.goToAccountPage(driver);

        //1.2. Fill in username and go to SignIn page
        accountPage.fillInUsername(driver, "sslove2cats@gmail.com");
        SignInPage signInPage = accountPage.goToPasswordPage(driver);

        //1.3. Fill in password
        signInPage.fillPassword(driver, "sslove2cats1");

        //1.4. Click sign In
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        //1.5. Verify user did sign in
        Assert.assertTrue("Inbox should exists", emailHomePage.isInboxExist(driver));



        Thread.sleep(10000);

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

        //10.1. Sign out
        signInPage = emailHomePage.signOut(driver);

        //10.2. Verify user did sign out
        Assert.assertTrue("Sign In page should show", signInPage.isSignInButtonExist(driver));

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
