package buoi_11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class uploadMultipleFile{
        static WebDriver driver;

        @BeforeClass
        public static void setUp(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://blueimp.github.io/jQuery-File-Upload/");
            driver.manage().window().maximize();
        }
        @Test
        public void uploadMultipleFileTest() throws InterruptedException {
            WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));

            // chọn nhìều ảnh cùng lúc, HTML input có multiple
            upload.sendKeys("/Users/giangqueman/Desktop/valentine.jpeg\n/Users/giangqueman/Desktop/lunar-new-year.jpg");
            Thread.sleep(2000);
        }
        @AfterClass
        public static void tearDown(){
            driver.quit();
        }

}
