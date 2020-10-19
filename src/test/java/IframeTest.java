import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//3. Write automation test which works with iframes
//        - Navigate to http://demo.guru99.com/Agile_Project/Agi_V1/index.php
//        - Find iframe with id=primis_playerSekindoSPlayer...
//        - Click on play button
//        - While video is playing move cursos in and out iframe
//        - Verify that when cursor is hovering iframe then stop button is visible, when cursor is not hovering - non visible

public class IframeTest extends BaseTest {
    String url = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
        driver.manage().window().maximize();
        waitForPageLoaded();
    }

    @Test
    public void PauseButtonViewCheck() {
        By playBtn = By.id("playBtn");
        By pauseBtn = By.id("pauseBtn");
        By backBtn = By.id("backBtn");
        By iframe = By.cssSelector("div[id^=primis_playerSekindoSPlayer]");
        WebElement locationToMoveIn = driver.findElement(backBtn);
        WebElement locationToMoveOut = driver.findElement(By.name("uid"));
        WebElement stopButtonCheck = driver.findElement(pauseBtn);

//        - Find iframe with id=primis_playerSekindoSPlayer...
        driver.findElement(iframe);
        Point point1 = locationToMoveIn.getLocation();
        int xIn = point1.getX();

        Point point2 = locationToMoveOut.getLocation();
        int xOut = point2.getX();
        int yOut = point2.getY();

//        - Click on play button
        driver.findElement(playBtn).click();

//        - While video is playing move cursor in and out iframe
        robot.mouseMove(xOut, yOut);
        Assert.assertFalse(stopButtonCheck.isDisplayed());

//        - Verify that when cursor is hovering iframe then stop button is visible, when cursor is not hovering - non visible
        action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();

        Dimension i = driver.manage().window().getSize();
        int yIn = i.getHeight();

//        robot.mouseMove(xIn, yIn-50);
//        The coordinates below were changed to make them near to our button, but coordinates above will work too.
        robot.mouseMove(xIn+40, yIn-50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pauseBtn));
        Assert.assertTrue(stopButtonCheck.isDisplayed());
//        driver.findElement(pauseBtn).click();
    }
}
