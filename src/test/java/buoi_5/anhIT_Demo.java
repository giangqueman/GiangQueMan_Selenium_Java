package buoi_5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class anhIT_Demo {

    public static void main (String [] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // mở trang web
        driver.get("https://anhitsolutions.com/login");
        driver.manage().window().maximize();

        // đăng nhập
        driver.findElement(By.id("username")).sendKeys("queman9498@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Wo0dy213!");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Thread.sleep(2000);

        // click cài đặt
        driver.findElement(By.xpath("//span[text()='Cài đặt']")).click();

        // xoá và nhập thông tin
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("queman9498@gmail.com");

        driver.findElement(By.name("full_name")).clear();
        driver.findElement(By.name("full_name")).sendKeys("Giang Quế Mẫn");

        // chọn dropdown
        WebElement languageDropdown = driver.findElement(By.xpath("//select[@name='language']"));
        Select select = new Select(languageDropdown);
        select.selectByValue("VI");

        driver.findElement(By.xpath("//div[@class='panel-content']//div[3]//a")).click();

        // nhập tiểu sử
        driver.findElement(By.name("about")).clear();
        driver.findElement(By.name("about")).sendKeys("Tôi tên Giang Quế Mẫn, sở thích của tôi là thích làm ra các sản phẩm handmade và đọc sách, tôi thích chơi cầu lông");

        // nhập tiêu đề
        driver.findElement(By.name("bio")).clear();
        driver.findElement(By.name("bio")).sendKeys("Full-stack Tester");

        // save
        driver.findElement(By.id("saveData")).click();

        // lấy giá trị thông báo sau khi save
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // đợi tối đa 2s
        WebElement toastHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2"))); // đợi cho đến khi tìm được thẻ h2 và lưu giá trị vào biến toastHeading
        String saveMessage = toastHeading.getText(); // tạo biến saveMessage kiểu string và getText
        System.out.println("thông báo sau khi save: " + saveMessage);

        // verify bio value
        WebElement bioValue = driver.findElement(By.name("bio"));
        String actualBioValue = bioValue.getText();
        Assert.assertEquals(actualBioValue, "Full-stack Tester");

        if(actualBioValue.equals("Full-stack Tester")) {
            System.out.println("Bio value is correct");
        } else {
            System.out.println("Bio value is incorrect");
        }

        // verify message
        String expectedSaveMessage = "save success";
       Assert.assertEquals("Save message is incorrect: expected <" + expectedSaveMessage
                       + "> but got <" + saveMessage + ">",
               saveMessage, expectedSaveMessage);

       if(saveMessage.equals(expectedSaveMessage)) {
           System.out.println("Save message is incorrect");
       } else {
           System.out.println("Save message is incorrect");
       }


    }
}
