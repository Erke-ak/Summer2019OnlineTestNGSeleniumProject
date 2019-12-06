package VytrackAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;

public class VytarckLogin {
    public static void main(String[] args) {
        //launch chrome browser
        WebDriver driver = BrowserFactory.getDriver("chrome");
        // go to Vytrack login page
        driver.get("https://qa2.vytrack.com/user/login");
        //to find username box
        WebElement username =driver.findElement(By.name("_username"));
        // entering the password
        username.sendKeys("storemanager79");
       // to find password box
        WebElement password =driver.findElement(By.name("_password"));
        //entering password
        password.sendKeys("UserUser123");
        //to find login button
        WebElement clicklogin =driver.findElement(By.id("_submit"));
        //clicking the login button
        clicklogin.click();

        //to test if the app login
        String expectedURL ="https://qa2.vytrack.com/";
        String actualURL =driver.getCurrentUrl();
        if(expectedURL.equals(actualURL)){
            System.out.println("Test Passed");
        }else{
            System.out.println("Test Failed");
        }
        driver.close(); //to close the tab
    }
}
