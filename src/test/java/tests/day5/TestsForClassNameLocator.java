package tests.day5;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
public class TestsForClassNameLocator {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
//          <h3 class="h3">Multiple buttons</h3>
//        h3 - it's a class name, or value of class attribute
        WebElement heading = driver.findElement(By.className("h3"));
//       we can read the text of attribute
        System.out.println(heading.getText());
        driver.quit();
    }
}
