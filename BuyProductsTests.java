package americanas;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BuyProductsTests {

    private static FirefoxDriver driver;

    @BeforeClass
    public static void openBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    @Before
    public void openPage(){
        driver.get("https://www.americanas.com.br");
    }

    @Test
    public void checkValueAndConditions(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("h_search-input")).sendKeys("Motorola G6");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"h_search\"]/div/div[1]/ul/li[1]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content-middle\"]/div[5]/div/div/div/div[2]/div[1]/section/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-buy"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-continue"))).click();

        Select qty = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/section/section/div[1]/div[1]/section/ul/li/div[2]/div[2]/div/select"))));
        qty.selectByIndex(2);

        final Double expected = 5000.00;
        Double total = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div[2]/div/div/span[2]")).getText().split(" ")[1].replaceAll("\\.","").replace(',','.'));
        Assert.assertTrue(total < expected);

        String expectedCondition = "Em até 10x s/ juros";
        String conditions = driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div[2]/div/div/div")).getText();
        Assert.assertEquals(expectedCondition, conditions);

        System.out.println("Finalizando teste.");
    }

}

