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

import java.util.List;

public class HomePageTests {
    private WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
    }

    @BeforeMethod
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testHomePageLoadTime() {
        test = extent.createTest("Test Home Page Load Time");
        try {
            long startTime = System.currentTimeMillis();
            driver.get("https://www.demoblaze.com/index.html");
            long endTime = System.currentTimeMillis();
            long loadTime = endTime - startTime;
            System.out.println("Ana sayfa yükleme süresi: " + loadTime + " milisaniye");
            Assert.assertTrue(loadTime < 5000, "Ana sayfa yükleme süresi 5 saniyeden uzun!");
            test.pass("Ana sayfa başarıyla yüklendi. Süre: " + loadTime + " ms");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testLogoClick() {
        test = extent.createTest("Test Logo Click Navigation");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By logo = By.xpath("/html[1]/body[1]/nav[1]/a[1]");
            driver.findElement(logo).click();
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://www.demoblaze.com/index.html", "Logo tıklaması ana sayfaya yönlendirmiyor!");
            test.pass("Logo tıklaması sonrası başarıyla ana sayfaya yönlendirildi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testNavbarContact() {
        test = extent.createTest("Test Navbar Contact Button");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By contactButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[2]/a[1]");
            driver.findElement(contactButton).click();
            test.pass("Contact button tıklandı ve işlem başarılı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testNavbarAboutUs() {
        test = extent.createTest("Test Navbar About Us Button");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By aboutUsButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[3]/a[1]");
            driver.findElement(aboutUsButton).click();
            test.pass("About Us button tıklandı ve işlem başarılı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testNavbarCart() {
        test = extent.createTest("Test Navbar Cart Button");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By cartButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[4]/a[1]");
            driver.findElement(cartButton).click();
            test.pass("Cart button tıklandı ve işlem başarılı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testNavbarLogin() {
        test = extent.createTest("Test Navbar Login Button");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By loginButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]");
            driver.findElement(loginButton).click();
            test.pass("Login button tıklandı ve işlem başarılı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testNavbarSignUp() {
        test = extent.createTest("Test Navbar Sign Up Button");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By signUpButton = By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[1]/li[8]/a[1]");
            driver.findElement(signUpButton).click();
            test.pass("Sign Up button tıklandı ve işlem başarılı.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCarouselImagesDisplayed() {
        test = extent.createTest("Test Carousel Images Displayed");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By carouselImages = By.cssSelector(".carousel-control-prev");
            int imageCount = driver.findElements(carouselImages).size();
            Assert.assertTrue(imageCount > 0, "Carousel'de hiçbir resim bulunamadı!");
            test.pass("Carousel'de resimler başarıyla görüntülendi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCarouselNavigationArrows() throws InterruptedException {
        test = extent.createTest("Test Carousel Navigation Arrows");
        try {
            driver.get("https://www.demoblaze.com/index.html");
            By leftArrow = By.cssSelector(".carousel-control-prev-icon");
            By rightArrow = By.cssSelector(".carousel-control-next-icon");
            driver.findElement(rightArrow).click();
            Thread.sleep(2000);
            WebElement activeSlideAfterRight = driver.findElement(By.cssSelector(".carousel-control-prev"));
            Assert.assertTrue(activeSlideAfterRight.isDisplayed(), "Sağ ok tıklaması sonrası carousel değişmedi!");
            driver.findElement(leftArrow).click();
            Thread.sleep(2000);
            WebElement activeSlideAfterLeft = driver.findElement(By.cssSelector(".carousel-control-prev"));
            Assert.assertTrue(activeSlideAfterLeft.isDisplayed(), "Sol ok tıklaması sonrası carousel değişmedi!");
            test.pass("Carousel okları başarıyla çalışıyor.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCategorySelection() throws InterruptedException {
        test = extent.createTest("Test Category Selection");
        try {
            driver.get("https://www.demoblaze.com/index.html");

            By phonesCategory = By.xpath("//a[contains(text(),'Phones')]");
            driver.findElement(phonesCategory).click();
            Thread.sleep(2000);

            List<WebElement> products = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(products.size() > 0, "Phones kategorisinde ürün bulunamadı!");

            By laptopsCategory = By.xpath("//a[contains(text(),'Laptops')]");
            driver.findElement(laptopsCategory).click();
            Thread.sleep(2000);

            List<WebElement> laptops = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertTrue(laptops.size() > 0, "Laptops kategorisinde ürün bulunamadı!");
            Assert.assertNotEquals(products.get(0).getText(), laptops.get(0).getText(), "Ürünler değişmedi!");

            test.pass("Kategori seçimi ve ürünlerin değişimi başarıyla test edildi.");
        } catch (Exception e) {
            test.fail("Test başarısız oldu: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testPaginationButtons() throws InterruptedException {
        test = extent.createTest("Test Pagination Buttons");
        try {
            driver.get("https://www.demoblaze.com/index.html");


            By nextButton = By.xpath("//button[contains(text(),'Next')]");
            driver.findElement(nextButton).click();
            Thread.sleep(2000);

            List<WebElement> firstPageProducts = driver.findElements(By.cssSelector(".card-title a"));
            driver.findElement(nextButton).click();
            Thread.sleep(2000);

            List<WebElement> secondPageProducts = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertNotEquals(firstPageProducts.get(0).getText(), secondPageProducts.get(0).getText(), "Sayfalar arasında ürünler değişmedi!");

            By previousButton = By.xpath("//button[contains(text(),'Previous')]");
            driver.findElement(previousButton).click();
            Thread.sleep(2000);

            List<WebElement> previousPageProducts = driver.findElements(By.cssSelector(".card-title a"));
            Assert.assertEquals(firstPageProducts.get(0).getText(), previousPageProducts.get(0).getText(), "Previous butonu doğru çalışmıyor!");

            test.pass("Pagination butonları başarıyla test edildi.");
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
