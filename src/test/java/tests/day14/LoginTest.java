package tests.day14;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.ConfigurationReader;
import utils.Driver;
public class LoginTest {
    @Test
    public void test1(){
        //read url value from the properties file
        String url = ConfigurationReader.getProperty("url");
        //Driver.get() --> will return webdriver object
        //and then we can call webdriver methods like get(), findElement()....
        Driver.get().get(url);
        BrowserUtils.wait(2);
        Driver.close();
    }
}
