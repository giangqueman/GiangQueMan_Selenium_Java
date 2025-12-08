package buoi_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IframeByWebElementDemo {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://anhitsolutions.com/contact");
        driver.manage().window().maximize();
    }

    @Test
    public void iframe() throws InterruptedException{
        // tìm iframe bằng xpath
        WebElement youtubeIframe = driver.findElement(By.xpath("//iframe[@title='YouTube video player']"));
        // switch vào iframe bằng webelement
        driver.switchTo().frame(youtubeIframe);
        // tìm nút play
        WebElement playButton = driver.findElement(By.xpath("//button[@title='Play']"));
        playButton.click();
        // dừng 4s
        Thread.sleep(4000);

        // switch khỏi iframe
        driver.switchTo().defaultContent();
        // vào tab Giảng viên
        WebElement teacher = driver.findElement(By.xpath("//a[text()='Giảng viên']"));
        teacher.click();

        Thread.sleep(3000);
        System.out.println("Title: "+ driver.getTitle());
    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
