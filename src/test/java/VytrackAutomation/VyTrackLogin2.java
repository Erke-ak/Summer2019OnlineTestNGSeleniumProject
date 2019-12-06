package VytrackAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;

public class VyTrackLogin2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login"); // go to VyTrack Login Page
        //driver.manage().window().fullscreen();
        driver.findElement(By.id("prependedInput")).sendKeys("abcde");
        driver.findElement(By.id("prependedInput2")).sendKeys("12341234");
        Thread.sleep(3000);
        driver.findElement(By.id("_submit")).click();
        // WebElement message = driver.findElement(By.cssSelector("[class='alert alert-error'] > div"));
        WebElement message = driver.findElement(By.xpath("//div[.='Invalid user name or password.']"));
        System.out.println(message.getText());
       /*
        String actualResult = driver.findElement(By.cssSelector("[class='alert alert-error']>div")).getAttribute("innerHTML");
        System.out.println(actualResult);
        */
        String expectedMessage = "Invalid user name or password.";
        String actualMessage = message.getText();
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        Thread.sleep(3000);
        driver.quit();

    }
}