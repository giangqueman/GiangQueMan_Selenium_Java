package buoi_8;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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

public class AlertDemo {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://anhit-basicform.surge.sh/");
        driver.manage().window().maximize();
    }

    @Test
    public void alertDemo(){
        // tìm button của alert để nhấn vào
        WebElement alertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        alertButton.click();

        // Chuyển sang alert và xử lý
        // đợi đến khi alert xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // lấy nội dung alert và bấm ok
        Alert alert = driver.switchTo().alert();
        System.out.println("alert notice: " + alert.getText());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();

        // tìm button confirm để click
        WebElement confirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        confirmButton.click();

        //  Chuyển sang alert và xử lý
        // click cancel
        Alert confirm = driver.switchTo().alert();
        confirm.dismiss();

        // lấy kết quả và so sánh
        String clickResult = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(clickResult, "You clicked: Cancel");
        System.out.println("ClickResult: " + clickResult);

        // tìm button JS promt
        WebElement promtButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        promtButton.click();

        // nhập vào hello và chọn ok
        Alert promt = driver.switchTo().alert();
        promt.sendKeys("Hello");
        promt.accept();

        // lấy giá trị đã nhập và so sánh
        String promtEnter = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(promtEnter, "You entered: Hello");
        System.out.println("promtEnter: " + promtEnter);
    }
    @Test
    public void testJsPromtCancel(){
        driver.get("http://anhit-basicform.surge.sh/");

        // tìm button JS promt
        WebElement promtButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        promtButton.click();

        // nhập vào hello và chọn cancel
        Alert promt = driver.switchTo().alert();
        promt.sendKeys("Hello");
        promt.dismiss();

        String promtCancel = driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(promtCancel, "You entered: null");
        System.out.println("promtCancel: " + promtCancel);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
