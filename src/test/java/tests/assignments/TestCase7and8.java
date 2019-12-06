package tests.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase7and8 {
    /*
    Test case #7 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. And click on “File Upload".
    Step 3. Upload any file with .txt extension from your computer.
    Step 4. Click “Upload” button.
    Step 5. Verify that subject is: “File Uploaded!”
    Step 6. Verify that uploaded file name is displayed.
    Note: use element.sendKeys(“/file/path”) with specifying path to the file for uploading. Run this method against “Choose File” button.
     Test case #8 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
     Step 2. And click on “Autocomplete”.
     Step 3. Enter “United States of America” into country input box.
    Step 4. Verify that following message is displayed: “You selected: United States of America”
     */
    private WebDriver driver;
    @BeforeMethod
    public void setup(){
        driver= BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
    }
    @Test
    public void Test7(){
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys("");
        driver.findElement(By.id("file-submit")).click();
        String expectedResult = "File Uploaded!";
        String actualResult =  driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
        Assert.assertEquals(actualResult,expectedResult,"File is not uploaded!");

    }
    @Test
    public  void Test8() {
        driver.findElement(By.linkText("Autocomplete")).click();

        driver.findElement(By.xpath("//input[@id='myCountry']")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@type='button']")).click();
        String expectedResult = "You selected: United States of America";
        String actualResult = driver.findElement(By.cssSelector("[id='result']")).getText();
        Assert.assertEquals(actualResult, expectedResult, "Message is not correct!");
        BrowserUtils.wait(2);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();




    }
}
