package buoi_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class WindowTabSwitchById {
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
        // lấy ID tab hiện tại
        String parentID = driver.getWindowHandle();
        System.out.println("Curent title: "+ driver.getTitle());

        // chờ đến khi element youtube xuất hiện và click youtube
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.youtube.com/c/OrangeHRMInc']"))).click();

        // lấy ID của tất cả các tab đang mở
        Set<String> allWindow = driver.getWindowHandles();
        // lặp qua từng tab, nếu ID của tab nào khác với parentID thì switch đến
        for (String windowID : allWindow){
            if (!windowID.equals(parentID)){
                driver.switchTo().window(windowID);
            }
        }
        Thread.sleep(2000);
        String newTabTitle = driver.getTitle();
        System.out.println("New tab title: " + newTabTitle);
        System.out.println("All window: " + driver.getWindowHandles().size());

        // đóng tab youtube
        driver.close();

        // trở về tab chính
        driver.switchTo().window(parentID);
        System.out.println("Back to parent window: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
