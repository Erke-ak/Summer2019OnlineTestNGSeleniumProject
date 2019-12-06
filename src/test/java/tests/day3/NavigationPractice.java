package tests.day3;

import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class NavigationPractice {
    public static void main(String[] args) {
       // create a webdriver object, to work with a browser
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize(); //to maximize browser window
        driver.get("http://google.com");
        //wait for 3 seconds
        //this is out custom method
        //since method is ststic, we use class name to call the method
        //as a parameter, we provide number of second(time in seconds)
        BrowserUtils.wait(3);
        //How To Print Page Title??
        System.out.println(driver.getTitle());
        driver.navigate().to("http://amazon.com");
        //navigate back to google
        driver.navigate().back();
        //move forward to the amazon again
        driver.navigate().forward();
        //to refresh/reload a webpage/website
        driver.navigate().refresh();
        //shutdown browser
        driver.quit();
        //what will happened, if I wil call driver again

        driver.get("http://google.com");

    }
}
