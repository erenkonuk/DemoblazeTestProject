package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DriverSetup;

public class CartPageTests {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
    }

    @BeforeMethod
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("cart-test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testPlaceOrderPopupDisplay() {
        test = extent.createTest("Test Place Order Popup Display");
        try {
            driver.get("https://www.demoblaze.com/cart.html");

            By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
            driver.findElement(placeOrderButton).click();

            WebElement placeOrderModal = driver.findElement(By.id("orderModal"));
            Assert.assertTrue(placeOrderModal.isDisplayed(), "Place Order popup görüntülenmedi!");

            test.pass("Place Order popup başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testPlaceOrderFormSubmission() {
        test = extent.createTest("Test Place Order Form Submission");
        try {
            driver.get("https://www.demoblaze.com/cart.html");

            By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
            driver.findElement(placeOrderButton).click();

            WebElement nameField = driver.findElement(By.id("name"));
            WebElement countryField = driver.findElement(By.id("country"));
            WebElement cityField = driver.findElement(By.id("city"));
            WebElement creditCardField = driver.findElement(By.id("card"));
            WebElement monthField = driver.findElement(By.id("month"));
            WebElement yearField = driver.findElement(By.id("year"));

            nameField.sendKeys("Test User");
            countryField.sendKeys("Test Country");
            cityField.sendKeys("Test City");
            creditCardField.sendKeys("1234-5678-9012-3456");
            monthField.sendKeys("12");
            yearField.sendKeys("2025");

            WebElement purchaseButton = driver.findElement(By.xpath("//button[contains(text(),'Purchase')]"));
            purchaseButton.click();

            WebElement confirmationMessage = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]"));
            Assert.assertTrue(confirmationMessage.isDisplayed(), "Sipariş başarıyla tamamlanmadı!");

            test.pass("Place Order form başarıyla gönderildi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCartEmptyState() {
        test = extent.createTest("Test Cart Empty State");
        try {
            driver.get("https://www.demoblaze.com/cart.html");

            WebElement cartTable = driver.findElement(By.id("tbodyid"));
            Assert.assertEquals(cartTable.getText().trim(), "", "Sepet boş değil!");

            test.pass("Sepet başarıyla boş olduğu doğrulandı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public static void tearDownReport() {
        extent.flush();
    }
}
