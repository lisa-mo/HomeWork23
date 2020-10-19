import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//4. Write automation test for drag&drop
//        - Navigate to http://demo.guru99.com/test/drag_drop.html
//        - Drag and drop all possible webelements to their placeholders

public class DragAndDropTest extends BaseTest {
    String url = "http://demo.guru99.com/test/drag_drop.html";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void dragAndDropAll() {

        WebElement from1 = driver.findElement(By.xpath("//*[@id='credit2']/a"));
        WebElement to1 = driver.findElement(By.xpath("//*[@id='bank']/li"));
        WebElement from2 = driver.findElement(By.xpath("//*[@id='credit1']/a"));
        WebElement to2 = driver.findElement(By.xpath("//*[@id='loan']/li"));
        WebElement from3 = driver.findElement(By.xpath("//*[@id='fourth']/a"));
        WebElement to3 = driver.findElement(By.xpath("//*[@id='amt7']/li"));
        WebElement from4 = driver.findElement(By.xpath("//*[@id='fourth']/a"));
        WebElement to4 = driver.findElement(By.xpath("//*[@id='amt8']/li"));

        action.dragAndDrop(from1, to1).build().perform();
        action.dragAndDrop(from2, to2).build().perform();
        action.dragAndDrop(from3, to3).build().perform();
        action.dragAndDrop(from4, to4).build().perform();

        if (driver.findElement(By.xpath("//a[contains(text(),'Perfect')]")).isDisplayed()) {
            System.out.println("Button Perfect! is displayed");
        } else {
            System.out.println("Button Perfect! is not displayed");
        }
    }
}