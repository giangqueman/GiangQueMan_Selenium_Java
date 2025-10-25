import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class safariDemo {
    public static void main(String[] args){
        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();

        driver.get("https://www.vietnamairlines.com");
        System.out.println("Tiêu đề: "+ driver.getTitle());
    }
}
