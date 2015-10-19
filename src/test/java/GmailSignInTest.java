import com.gmailtest.pageobjects.EmailHomePage;
import com.gmailtest.pageobjects.EmailViewPage;
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

        //1.1 Go to Gmail website
        AccountPage accountPage = WebUtil.goToAccountPage(driver);

        //1.2. Fill in username and go to SignIn page
        accountPage.fillInUsername(driver, "sslove2cats@gmail.com");
        SignInPage signInPage = accountPage.goToPasswordPage(driver);

        //1.3. Fill in password
        signInPage.fillPassword(driver, "sslove2cats1");

        //1.4. Click sign In
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        Thread.sleep(10000);

        //2. Click Compose
        emailHomePage.clickComposeButton(driver);

        //3. Fill in recipient
        emailHomePage.fillingRecipient(driver, "sslove2cats@gmail.com");

        //4. Fill in subject
        final String subjectText = "Gmail Send Email Test";
        emailHomePage.fillingSubject(driver, subjectText);

        //5. Fill in email body
        final String bodyText = "Gmail Send Email Body Test";
        emailHomePage.fillingBody(driver, bodyText);

        //6. Click Send
        emailHomePage.clickSendEmail(driver);

        //7. Click Inbox again
        emailHomePage.clickInboxWithNewEmail(driver, "Inbox (1)");

        //8. Click email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver);

        //9. Verify the email subject and email body is correct
        String actualSubjectText = emailViewPage.getEmailSubjectText(driver);
        Assert.assertEquals("Email subject should be the same", subjectText, actualSubjectText);

        String actualBodyText = emailViewPage.getEmailBodyText(driver);
        Assert.assertEquals("Email Body should be the same", bodyText, actualBodyText);

        //10. Sign out
        emailHomePage.signOut(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
