package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.util.List;

public class SauceLabsTest extends TestBase {


    @Test(priority = 1)

    public void validateLoginTest() {
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String actualPageHeader = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expectedPageHeader = "PRODUCTS";

        Assert.assertEquals(actualPageHeader, expectedPageHeader);
    }

    @Test (priority = 2)
    public void validateFilterByPriceTest() {
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement filter = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(filter);
        select.selectByValue("lohi");

        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        // [$7.99 - $9.99 - $15.99 - $29.99 - $49.99]

        for (int i = 1; i < prices.size(); i++) {
            String price = prices.get(i).getText().substring(1); // "$9.99" ->"$9.99"
            double priceDouble = Double.parseDouble(price); // "$9.99" ->"$9.99"

            String price2 = prices.get(i - 1).getText().substring(1); // "$7.99" ->"$7.99"
            double priceDouble2 = Double.parseDouble(price2); // "$7.99" ->"$7.99"

            Assert.assertTrue(priceDouble >= priceDouble2);


        }
    }

        @Test(priority = 3)
        public void validateOrderFunctionalityTest() {
            driver.get(ConfigReader.getProperty("SauceLabsURL"));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
            driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
            String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
            driver.findElement(By.id("checkout")).click();

            driver.findElement(By.id("first-name")).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Doe");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.id("continue")).click();

            String checkOutPrice = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();

            SoftAssert softAssert = new SoftAssert();
            // Item total:$29.99 -> $29.99
            Assert.assertEquals(checkOutPrice.substring(checkOutPrice.lastIndexOf(" ") + 1), price);

            driver.findElement(By.id("finish")).click();

            String actualSuccessMessage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();

            String expectedSuccessMessage = "THANK YOU FOR YOUR ORDER";

            Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);

            softAssert.assertAll();

        }

    }








