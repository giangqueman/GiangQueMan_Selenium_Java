import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo_ID {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get(("https://live.techpanda.org/index.php/customer/account/login")); // truy cap trang web
        driver.findElement(By.id("email")).sendKeys("abc@gmail.com"); // nhap email kiem tra


    }
}
