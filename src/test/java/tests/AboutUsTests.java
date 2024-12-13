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

public class AboutUsTests {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
    }

    @BeforeMethod
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("about-us-test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testAboutUsPopupDisplay() {
        test = extent.createTest("Test About Us Popup Display");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By aboutUsButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[3]/a[1]");
            driver.findElement(aboutUsButton).click();

            WebElement aboutUsModal = driver.findElement(By.id("videoModal"));
            Assert.assertTrue(aboutUsModal.isDisplayed(), "About Us popup görüntülenmedi!");

            test.pass("About Us popup başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testVideoPlayback() throws InterruptedException {
        test = extent.createTest("Test Video Playback in About Us Popup");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By aboutUsButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[3]/a[1]");
            driver.findElement(aboutUsButton).click();

            WebElement videoElement = driver.findElement(By.tagName("video"));
            Assert.assertTrue(videoElement.isDisplayed(), "Video görüntülenmedi!");

            videoElement.click();
            Thread.sleep(2000);
            String currentTime = videoElement.getAttribute("currentTime");
            Assert.assertNotEquals(currentTime, "0", "Video oynatılmadı!");

            test.pass("Video About Us popup içerisinde başarıyla oynatıldı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCloseAboutUsPopup() {
        test = extent.createTest("Test Close About Us Popup");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By aboutUsButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[3]/a[1]");
            driver.findElement(aboutUsButton).click();

            WebElement closeButton = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
            closeButton.click();

            WebElement aboutUsModal = driver.findElement(By.id("videoModal"));
            Assert.assertFalse(aboutUsModal.isDisplayed(), "About Us popup kapatılmadı!");

            test.pass("About Us popup başarıyla kapatıldı.");
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
