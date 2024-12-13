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

public class SignUpPopUpTests {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
    }

    @BeforeMethod
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("signup-popup-test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testSignUpPopupDisplay() {
        test = extent.createTest("Test Sign Up Popup Display");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By signUpButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[8]/a[1]");
            driver.findElement(signUpButton).click();

            WebElement signUpModal = driver.findElement(By.id("signInModal"));
            Assert.assertTrue(signUpModal.isDisplayed(), "Sign Up popup görüntülenmedi!");

            test.pass("Sign Up popup başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSignUpFormSubmission() throws InterruptedException {
        test = extent.createTest("Test Sign Up Form Submission");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By signUpButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[8]/a[1]");
            driver.findElement(signUpButton).click();

            WebElement usernameField = driver.findElement(By.id("sign-username"));
            WebElement passwordField = driver.findElement(By.id("sign-password"));

            usernameField.sendKeys("newuser");
            passwordField.sendKeys("newpassword");

            WebElement signUpSubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
            signUpSubmitButton.click();

            Thread.sleep(2000); // Bekleme, kayıt işleminin tamamlanması için

            Assert.assertTrue(driver.switchTo().alert().getText().contains("Sign up successful"), "Sign Up başarısız oldu!");
            driver.switchTo().alert().accept();

            test.pass("Sign Up form başarıyla gönderildi ve kullanıcı kaydı tamamlandı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSignUpPopupClose() {
        test = extent.createTest("Test Sign Up Popup Close");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By signUpButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[8]/a[1]");
            driver.findElement(signUpButton).click();

            WebElement closeButton = driver.findElement(By.xpath("//div[@id='signInModal']//button[contains(text(),'Close')]"));
            closeButton.click();

            WebElement signUpModal = driver.findElement(By.id("signInModal"));
            Assert.assertFalse(signUpModal.isDisplayed(), "Sign Up popup kapatılmadı!");

            test.pass("Sign Up popup başarıyla kapatıldı.");
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
