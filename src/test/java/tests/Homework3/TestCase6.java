package tests.Homework3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase6 {

    @Test
    public void test6(){

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://www.tempmailaddress.com/");
        String email = driver.findElement(By.xpath("//span[@id='email']")).getText();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys("Yasmin");
        driver.findElement(By.name("email")).sendKeys(email);

        driver.findElement(By.className("radius")).click();
        String expectedResult = "Thank you for signing up. Click the button below to return to the home page.";
        String actualResult = driver.findElement(By.name("signup_message")).getText();

        Assert.assertEquals(actualResult,expectedResult,"Message is not correct");
        BrowserUtils.wait(2);
        driver.navigate().to("https://www.tempmailaddress.com/");
        BrowserUtils.wait(2);

        driver.findElement(By.xpath("// tbody[@id='schranka']/tr[3]/td/span[2]")).click();

        BrowserUtils.wait(2);

        String expectedReceiveEmail = "do-not-reply@practice.cybertekschool.com";

        String actualReceivedEmail = driver.findElement(By.cssSelector("#odesilatel")).getText();

        Assert.assertEquals(actualReceivedEmail,expectedReceiveEmail,"Received email is incorrect");

        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";

        String actualSubject = driver.findElement(By.cssSelector("#predmet")).getText();

        Assert.assertEquals(actualSubject,expectedSubject,"Subject is not correct");
        BrowserUtils.wait(2);







    }
}
