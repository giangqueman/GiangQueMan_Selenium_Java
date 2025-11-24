package buoi_6;

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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class DefaultDropdownDemo {
    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        //mở trình duyệt chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // mở trang web và mở to màn hình
        driver.get("https://egov.danang.gov.vn/reg");
        driver.manage().window().maximize();

    }

    @Test
    public void dropdownTest() throws InterruptedException {
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='thuongtru_tinhthanh']")); // tìm xpath của dropdown
        Select selectCity = new Select(dropdownElement); // tạo đối tượng Select để chứa các option bên trong dropdown
        // chọn tpHCM
        selectCity.selectByVisibleText("thành phố Hồ Chí Minh");

        // kiểm tra selectCity có được multi select không
        Assert.assertFalse(selectCity.isMultiple());
        System.out.println("City dropdown is multi select: " + selectCity.isMultiple());

        // kiểm tra giá trị đã chọn
        String currentCity = selectCity.getFirstSelectedOption().getText();
        Assert.assertEquals(currentCity, "thành phố Hồ Chí Minh");
        System.out.println("Current city: " + currentCity);

        // in ra số tổng tỉnh/ tp
        List<WebElement> listCity = selectCity.getOptions();
        System.out.println("Total cities: " +listCity.size());

        // in ra các tỉnh/ tp
        for(WebElement cityItem : listCity){
            System.out.println("Cites name: " + cityItem.getText());
        }

        // chọn quận/ huyện
        // chở đến khi option quân/ huyện xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='thuongtru_quanhuyen']/option"),1));

        WebElement dropdownDistrict = driver.findElement(By.xpath("//select[@id='thuongtru_quanhuyen']"));
        Select selectDistrict = new Select(dropdownDistrict);
        //chọn quận 5 theo value
        selectDistrict.selectByValue("10015");

        // kiểm tra selectDistrict có được multi select không
        Assert.assertFalse(selectDistrict.isMultiple());
        System.out.println("District dropdown is multi select: " + selectCity.isMultiple());
//
        // kiểm tra giá trị đã chọn
        String currentDistrict = selectDistrict.getFirstSelectedOption().getText();
        Assert.assertEquals(currentDistrict, "quận 5");


        // chọn phường
        // chờ đến khi option phường xuất hiện
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='thuongtru_phuongxa']/option"),1));

        WebElement dropdownWard = driver.findElement(By.xpath("//select[@id='thuongtru_phuongxa']"));
        Select selectWard = new Select(dropdownWard);
        // chọn phường theo index
        selectWard.selectByIndex(3);

        // kiểm tra selectWard có được multi select không
        Assert.assertFalse(selectWard.isMultiple());
        System.out.println("Ward dropdown is multi select: " + selectCity.isMultiple());

        // kiểm tra giá trị đã chọn

        String currentWard = selectWard.getFirstSelectedOption().getText();
        System.out.println("Current ward: " + currentWard); // in giá trị phường đang chọn so sánh
        Assert.assertEquals(currentWard, "phường 02");


        Thread.sleep(5000);

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}


