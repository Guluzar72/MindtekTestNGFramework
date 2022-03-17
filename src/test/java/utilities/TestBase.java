package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

   protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=Driver.getDriver();
    }
    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(6000);
        driver.quit();
    }
}

