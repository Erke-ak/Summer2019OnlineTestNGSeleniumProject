package tests.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class RegistrationFormTestNG {
    /*
    Test case #1 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed: “The date of birth is not valid”

     */
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Registration Form")).click();
    }

    @Test
    public void testCase1() {
        String expectedMessage = "The date of birth is not valid";
        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("wrong_dob");
        String actualMessage = driver.findElement(By.xpath("//small[text() = 'The date of birth is not valid']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Result is wrong");

    }

    @Test
    public void testCase2() {
        String[] expectedLanguages = {"C++", "Java" ,"JavaScript"};
        List<WebElement> languages = driver.findElements(By.xpath("//*[@id='registrationForm']/div[11]/div"));
        for (WebElement language :languages ){
            String actualLanguage = language.getText();
            BrowserUtils.wait(3);
            Assert.assertEquals(actualLanguage,expectedLanguages,"Language is not correct");
        }
    }

    @Test
    public void testCase3() {
        String expectedMessage = "first name must be more than 2 and less than 64 characters long";
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("a");
        String actualMessage = driver.findElement(By.xpath("//form[@id='registrationForm']/div[1]/div/small[2]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Result is wrong");

    }

    @Test
    public void testCase4() {
        String expectedMessage = "The last name must be more than 2 and less than 64 characters long";
        driver.findElement(By.name("lastname")).sendKeys("a");
        String actualMessage = driver.findElement(By.xpath("//form[@id='registrationForm']/div[2]/div/small[2]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Result is wrong");
    }

    @Test
    public void testCase5() {
        String expectedMessage = "You've successfully completed registration!";
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Erke");
        driver.findElement(By.name("lastname")).sendKeys("Agzamkhan");
        driver.findElement(By.name("username")).sendKeys("erke01");
        driver.findElement(By.name("email")).sendKeys("erk01@email.com");
        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.name("phone")).sendKeys("222-123-4567");
        driver.findElement(By.xpath("//input[@value='female']")).click();
        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("11/11/1990");
        Select option = new Select(driver.findElement(By.name("department")));
        option.selectByVisibleText("Department of Engineering");
        Select s = new Select(driver.findElement(By.name("job_title")));
        s.selectByVisibleText("SDET");
        driver.findElement(By.xpath("//input[@id='inlineCheckbox2']")).click();
        driver.findElement(By.id("wooden_spoon")).click();
        String actualMessage = driver.findElement(By.xpath("//*[@id='content']/div/div/p")).getText();
        BrowserUtils.wait(3);
        Assert.assertEquals(actualMessage, expectedMessage, "Result is wrong");


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


}
