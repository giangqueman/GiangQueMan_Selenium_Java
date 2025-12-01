package buoi_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckboxDemo {
     static WebDriver driver;

     @BeforeClass
    public static void setUp(){
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.get("https://demo.nopcommerce.com/register");
         driver.manage().window().maximize();
     }

     @Test
    public void register(){
         WebElement firstName = driver.findElement(By.id("FirstName"));
         firstName.sendKeys("Man");

         WebElement lastName = driver.findElement(By.id("LastName"));
         lastName.sendKeys("Giang");

         WebElement email = driver.findElement(By.id("Email"));
         email.sendKeys("mangiang@gmail.com");

         WebElement registerButton = driver.findElement(By.id("register-button"));
         registerButton.click();

         //
         boolean buttonEnabled = registerButton.isEnabled();
         Assert.assertTrue(buttonEnabled);
         System.out.println("Register button is enabled");

         // checkbox giới tính
         WebElement  genderCheckbox = driver.findElement(By.xpath("//input[@id='gender-female']"));
         // kiểm tra nếu checkbox chưa chọn
         if(!genderCheckbox.isSelected()){
             // chọn checkbox
             genderCheckbox.click();
         }
         // kiểm tra checkbox đã được chọn
         boolean isChecked = genderCheckbox.isSelected();
         System.out.println("Checkbox selected? " + isChecked);
         // checkbox chọn option nào
         WebElement genderCheckboxLabel = driver.findElement(By.xpath("//input[@id='gender-female']//following-sibling::label"));
         System.out.println("Gender checkbox: " + genderCheckboxLabel.getText());

         // checkbox newsletter
         WebElement newsletterChecbox = driver.findElement(By.xpath("//input[@id='NewsLetterSubscriptions_0__IsActive']"));
         // kiem tra checkbox da chon va bo chon
         Assert.assertTrue(newsletterChecbox.isSelected());
         newsletterChecbox.click();
         System.out.println("Newsletter checkbox is not selected");

         // lấy message khi bấm nút register mà không nhập password
         String expectedMessage = "Password is required.";
         Assert.assertEquals(expectedMessage, driver.findElement(By.id("ConfirmPassword-error")).getText());
         System.out.println("Message: " + expectedMessage);
     }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}

