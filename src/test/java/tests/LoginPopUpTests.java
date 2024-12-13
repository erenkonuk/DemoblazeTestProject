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

public class LoginPopUpTests {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
    }

    @BeforeMethod
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("login-popup-test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testLoginPopupDisplay() {
        test = extent.createTest("Test Login Popup Display");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By loginButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]");
            driver.findElement(loginButton).click();

            WebElement loginModal = driver.findElement(By.id("logInModal"));
            Assert.assertTrue(loginModal.isDisplayed(), "Login popup görüntülenmedi!");

            test.pass("Login popup başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testLoginFormSubmission() throws InterruptedException {
        test = extent.createTest("Test Login Form Submission");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By loginButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]");
            driver.findElement(loginButton).click();

            WebElement usernameField = driver.findElement(By.id("loginusername"));
            WebElement passwordField = driver.findElement(By.id("loginpassword"));

            usernameField.sendKeys("testuser");
            passwordField.sendKeys("testpassword");

            WebElement loginSubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
            loginSubmitButton.click();

            Thread.sleep(2000);

            WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(),'Log out')]"));
            Assert.assertTrue(logoutButton.isDisplayed(), "Login başarısız!");

            test.pass("Login form başarıyla gönderildi ve giriş yapıldı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testLoginPopupClose() {
        test = extent.createTest("Test Login Popup Close");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By loginButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]");
            driver.findElement(loginButton).click();

            WebElement closeButton = driver.findElement(By.xpath("//div[@id='logInModal']//button[contains(text(),'Close')]"));
            closeButton.click();

            WebElement loginModal = driver.findElement(By.id("logInModal"));
            Assert.assertFalse(loginModal.isDisplayed(), "Login popup kapatılmadı!");

            test.pass("Login popup başarıyla kapatıldı.");
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
