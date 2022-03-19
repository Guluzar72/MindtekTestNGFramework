package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppCartPage;
import pages.StoreAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;

public class StoreAppCartFunctionalityTest extends TestBase {

    @Test
    public void validateAddCartFunctionalityTest() throws IOException, InterruptedException {

        driver.get(ConfigReader.getProperty("StoreAppURL"));
        BrowserUtils.scrollUpOrDown(500);

        StoreAppHomePage homePage=new StoreAppHomePage();
        BrowserUtils.hoverOver(homePage.product1);

        homePage.product1AddCart.click();

        String actualMessage=homePage.addCardSuccessMessage.getText();
        String expectedMessage="Product successfully added to your shopping cart";

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);
        Thread.sleep(10000);
        BrowserUtils.takeScreenshot();
        String addedProductName=homePage.addedProductName.getText();

        homePage.closingWindowIcon.click();
        homePage.cart.click();

        StoreAppCartPage cartPage=new StoreAppCartPage();
        String addedProductNameInCart = cartPage.productName.getText();

        softAssert.assertEquals(addedProductNameInCart,addedProductName);

        softAssert.assertAll();



                
    }

    }

