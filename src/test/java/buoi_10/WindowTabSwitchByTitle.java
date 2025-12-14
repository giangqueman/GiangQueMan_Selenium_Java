package buoi_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowTabSwitchByTitle {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }
    @Test
    public void newWindow() throws InterruptedException{
        String parentTab = driver.getWindowHandle();
        System.out.println("Current tab: " + driver.getTitle());

        // chờ đến khi element youtube xuất hiện và click youtube
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.youtube.com/c/OrangeHRMInc']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://twitter.com/orangehrm?lang=en']"))).click();
        Thread.sleep(2000);

        // tab muốn chuyển đến
        String expectedYoutubeTab = "OrangeHRM Inc - YouTube";

        // lấy handle tất cả các tab đang mở
        Set<String> windowHandle = driver.getWindowHandles();

        // lặp qua từng tab, nếu title của tab nào đúng với expectedTitle thì switch đến
        for(String handle : windowHandle){
            driver.switchTo().window(handle);

            if(driver.getTitle().equals(expectedYoutubeTab)){ // nếu đến tab có title là youtube thì dừng
                break;
            }
        }
        System.out.println("Đã chuyển đến tab: " + driver.getTitle());

        // tắt tất cả tab con
        for(String runWindow : windowHandle){
            if(!runWindow.equals(parentTab)){ // so sánh các tab con đang mở khác với tab cha thì tắt
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        System.out.println("All window: " + driver.getWindowHandles().size());

        // switch về tab cha
        driver.switchTo().window(parentTab);
        // thao tác với tab cha
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Login to OrangeHRM successfully");
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");

    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
