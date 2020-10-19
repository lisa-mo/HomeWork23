import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;
//2. Write automation test which handle cookies
//        - Login to http://demo.guru99.com/Agile_Project/Agi_V1/index.php
//        - Print out all cookies (all data)
//        - Clear all cookies
//        - Refresh page, verify session of authorization still exists

public class CookiesTest extends BaseTest {
    String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String pass = "Guru99";

    @BeforeMethod
    public void loginToUrl() {
        driver.get(loginUrl);
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        waitForPageLoaded();
    }

    @Test
    public void cookiesChecking() {
        String currentUrl = driver.getCurrentUrl();
        driver.navigate().to(currentUrl);

        for (Cookie ck : driver.manage().getCookies()) {
            System.out.println(("Name: " + ck.getName() + "; Value: " + ck.getValue() + "; Domain: " + ck.getDomain() + "; Path: " + ck.getPath() + "; Expiry: " + ck.getExpiry() + "; isSecure: " + ck.isSecure() + "; isHTTpOnly: " + ck.isHttpOnly()));

            driver.manage().deleteAllCookies();
            Set<Cookie> cookies = driver.manage().getCookies();
            Assert.assertTrue(cookies.isEmpty());

            driver.navigate().refresh();

            By logoutButton = By.linkText("Log out");
            wait.until(presenceOfElementLocated(logoutButton));
            assertTrue(driver.findElement(logoutButton).isDisplayed());
        }
    }
}
