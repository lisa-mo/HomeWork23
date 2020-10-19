
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
//1. Write automation test for mail.google.com
//        - Login to mail.google.com with existing account
//        - Create new email (with TO=*the same email*, subject, email text), attach file from your local machine, click send
//        - Go to inbox, verify email came, verify subject, content of email, verify file is attached

public class GmailTest extends BaseTest {
    String url = "https://mail.google.com";
    String login = "testingtesting1789@gmail.com";
    String pass = "GH6#$gk%&fj5Tybl*";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void gmailNewEmail() {

        int number = (int) (Math.random() * 2000);
        String subject = Integer.toString(number);
        String emailText = "This is a test email.";
        driver.findElement(By.name("identifier")).sendKeys(login + Keys.ENTER);
        waitForPageLoaded();
        driver.findElement(By.name("password")).sendKeys(pass + Keys.ENTER);
        waitForPageLoaded();
        wait.until(presenceOfElementLocated(By.cssSelector("div.aim.ain")));

        driver.findElement(By.className("z0")).click();

        wait.until(elementToBeClickable(By.cssSelector("div[command=Files]")));
        driver.findElement(By.className("vO")).sendKeys("testingtesting1789@gmail.com");
        driver.findElement(By.className("aoT")).sendKeys(subject);
        driver.findElement(By.className("Am")).sendKeys(emailText);
        driver.findElement(By.cssSelector("div[command=Files]")).click();

        sleep(2000);
        StringSelection strSelection = new StringSelection("emailTest.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        wait.until(elementToBeClickable(By.cssSelector("div.vq")));
        driver.findElement(By.cssSelector("div.dC")).click();

        waitForPageLoaded();
        String emailTopic = "//span[text()='" + subject + "']";
        waitingForObject(emailTopic);
        wait.until(presenceOfElementLocated(By.xpath(emailTopic)));

        waitingForObject("//*[@class='bog']//span[text()='" + subject + "']");
        driver.findElement(By.xpath("//*[@class='bog']//span[text()='" + subject + "']")).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("img[title='emailTest.txt']")));
        Assert.assertTrue(isCSSElementPresent("img[title='emailTest.txt']"));
        String testText = driver.findElement(By.xpath("//div[text()='" + emailText + "']")).getText();

        Assert.assertEquals(emailText, testText);
    }
}
