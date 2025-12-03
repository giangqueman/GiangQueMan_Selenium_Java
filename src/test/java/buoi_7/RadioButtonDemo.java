package buoi_7;

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

public class RadioButtonDemo {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }
    @Test
    public static void radioButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // đợi đến khi tìm được xpath username và nhập username
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        username.sendKeys("Admin");

        // đợi đến khi tìm được xpath password và nhập password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        password.sendKeys("admin123");

        // đợi đến khi tìm được button login và click
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        loginButton.click();

        // radio button
        WebDriverWait wait_2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement myInfo = wait_2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='My Info']")));
        myInfo.click();

        WebDriverWait wait_3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement genderRadio = wait_3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Male']/span")));
        genderRadio.click();
        Assert.assertFalse(genderRadio.isSelected());
        System.out.println("Gender is checked: " + genderRadio.isSelected());
    }
    @AfterClass
    public static void tearDown(){
        try {
            Thread.sleep(5000); // chờ 5 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
