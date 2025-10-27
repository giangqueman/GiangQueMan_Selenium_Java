import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo_Xpath {
    public static void main(String [] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(); // mở trình duyệt chrome

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // truy cập trang web

        //Dừng chương trình 1 giây
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // dùng Xpath để tìm element và kiểm tra
        // Xpath tuyệt đối
        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");

        // Xpath tương đối
        driver.findElement(By.xpath("//input[@name= 'password']")).sendKeys("admin123");

    }
}
