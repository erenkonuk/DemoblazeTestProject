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

public class ContactPopUpTests {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
    }

    @BeforeMethod
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("contact-popup-test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testContactPopupDisplay() {
        test = extent.createTest("Test Contact Popup Display");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By contactButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]");
            driver.findElement(contactButton).click();

            WebElement contactModal = driver.findElement(By.id("exampleModal"));
            Assert.assertTrue(contactModal.isDisplayed(), "Contact popup görüntülenmedi!");

            test.pass("Contact popup başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testContactFormFields() {
        test = extent.createTest("Test Contact Form Fields");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By contactButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]");
            driver.findElement(contactButton).click();

            WebElement emailField = driver.findElement(By.id("recipient-email"));
            WebElement nameField = driver.findElement(By.id("recipient-name"));
            WebElement messageField = driver.findElement(By.id("message-text"));

            Assert.assertTrue(emailField.isDisplayed(), "Email alanı görüntülenmedi!");
            Assert.assertTrue(nameField.isDisplayed(), "Name alanı görüntülenmedi!");
            Assert.assertTrue(messageField.isDisplayed(), "Message alanı görüntülenmedi!");

            test.pass("Contact form alanları başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testContactFormSubmission() throws InterruptedException {
        test = extent.createTest("Test Contact Form Submission");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By contactButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]");
            driver.findElement(contactButton).click();

            WebElement emailField = driver.findElement(By.id("recipient-email"));
            WebElement nameField = driver.findElement(By.id("recipient-name"));
            WebElement messageField = driver.findElement(By.id("message-text"));

            emailField.sendKeys("test@example.com");
            nameField.sendKeys("Test User");
            messageField.sendKeys("This is a test message.");

            WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send message')]"));
            sendButton.click();

            Thread.sleep(2000);
            Assert.assertTrue(driver.switchTo().alert().getText().contains("Thanks"), "Form gönderimi başarısız!");
            driver.switchTo().alert().accept();

            test.pass("Contact form başarıyla gönderildi.");
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
