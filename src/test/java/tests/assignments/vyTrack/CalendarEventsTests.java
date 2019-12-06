package tests.assignments.vyTrack;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarEventsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        wait = new WebDriverWait(driver, 12);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        BrowserUtils.wait(2);
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        calendarEventsElement.click();
        WebElement loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));

    }

    @Test(description = "Verify that page subtitle 'Options' is displayed")
    public void testCase1() {
        WebElement optionsButton = driver.findElement(By.cssSelector("[class='btn btn-link dropdown-toggle']"));

        Assert.assertTrue(optionsButton.isDisplayed());


    }

    @Test(description = "Verify that page number is equals to '1'")
    public void testCase2() {
        WebElement numberOfPages = driver.findElement(By.cssSelector("[type='number']"));
        String Value = numberOfPages.getAttribute("value");
        Assert.assertTrue(Value.equals("1"));


    }

    @Test(description = "Verify that view per page number is equals to '25'")
    public void testCase3() {
        WebElement ViewPerPage = driver.findElement(By.cssSelector("[class='btn dropdown-toggle ']"));
        String ViewPerPageText = ViewPerPage.getText();
        Assert.assertTrue(ViewPerPageText.equals("25"));


    }

    @Test(description = "Verify that number of calendar events (rows in the table) is equals to number of records")
    public void testCase4() {
        List<WebElement> Rows = driver.findElements(By.xpath("//tbody//tr"));
        int numberOfRows = Rows.size();
        WebElement Records = driver.findElement(By.cssSelector("[class='dib']:nth-of-type(3)"));
        String RecordsText = Records.getText();
        Assert.assertTrue(RecordsText.contains("" + numberOfRows));


    }
    @Test(description = "Verify that all calendar events were selected")
    public void testCase5(){
        driver.findElement(By.xpath("//div[@class='btn-group dropdown']/button/input")).click();

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//tbody/tr/td/input"));
        for (WebElement checkBox:checkBoxes) {
            Assert.assertTrue(checkBox.isSelected());

        }

    }
    @Test(description = "Verify that meeting data is displayed when clicked on meeting")



    public void testCase6() {
        WebElement meeting = driver.findElement(By.xpath("//tbody//tr[13]//td[2]"));
        meeting.click();
        WebElement loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));

        String[] info = {"Title", "Testers Meeting", "Description", "Start", "Nov 27, 2019, 9:30 PM", "End", "Nov 27, 2019, 10:30 PM",
                "All-day Event", "No", "Organizer", "Stephan Haley", "Guests", "Tom Smith - Required", "Recurrence",
                "Weekly every 1 week on Wednesday", "Call Via Hangout", "No", "This is a a weekly testers meeting"};

        List<WebElement> data = driver.findElements(By.cssSelector("[class='control-label']"));
        int count = 0;
        for (WebElement each : data) {
            Assert.assertEquals(each.getText(), info[count], "Info does not match");
            Assert.assertTrue(each.isDisplayed());
            count++;
        }
        WebElement description = driver.findElement(By.cssSelector("[class='control-label html-property']"));
        String actualDescription = description.getText();
        String expectedDescription = info[17];
        Assert.assertEquals(actualDescription, expectedDescription, "Description is wrong");


    }


    @AfterMethod
    public void teardown(){
        driver.quit();

    }
}
