package americanas;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UserRegisterTests {

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
        WebElement element = driver.findElement(By.xpath("//*[@id=\"h_user\"]/span[2]/div/a[2]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    @Test
    public void emailAlreadyRegistered() {
        final String TEST_NAME = "Verificando se mensagem é exibida ao inserir email existente no cadastro de novo usuário.";
        System.out.println(TEST_NAME);

        final String EXPECTED_MESSAGE = "E-mail já cadastrado";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-input"))).sendKeys("teste@hotmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-input"))).sendKeys("12");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/form/button")).click();
        Assert.assertEquals(EXPECTED_MESSAGE, driver.findElement(By.xpath("//*[@id=\"email\"]/div")).getText());

        System.out.println("Finalizando teste "  + TEST_NAME);
    }

    @Test
    public void shortPassword() {
        final String TEST_NAME = "Verificando se mensagem é exibida ao inserir senha com menos caracteres que o obrigatório.";
        System.out.println(TEST_NAME);

        final String EXPECTED_MESSAGE = "Senha precisa ter entre 6 a 50 caracteres.";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-input"))).sendKeys("jaquelinejennifertatianeoliveira@oana.com.br");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-input"))).sendKeys("12");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/form/button")).click();
        Assert.assertEquals(EXPECTED_MESSAGE, driver.findElement(By.xpath("//*[@id=\"password\"]/div[2]")).getText());

        System.out.println("Finalizando teste "  + TEST_NAME);
    }

    @Test
    public void invalidCPF() {
        final String TEST_NAME = "Verificando se mensagem é exibida ao inserir CPF inválido.";
        System.out.println(TEST_NAME);

        final String EXPECTED_MESSAGE = "Campo inválido";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-input"))).sendKeys("jaquelinejennifertatianeoliveira@oana.com.br");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-input"))).sendKeys("12");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cpf-input"))).sendKeys("11111111111");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/form/button")).click();
        Assert.assertEquals(EXPECTED_MESSAGE, driver.findElement(By.xpath("//*[@id=\"cpf\"]/div")).getText());

        System.out.println("Finalizando teste "  + TEST_NAME);
    }

    @Test
    public void successfullRegister(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-input"))).sendKeys("sabrinaflaviaolivei@sistectecnologia.com.br");
        driver.findElement(By.id("password-input")).sendKeys("senha123456");
        driver.findElement(By.id("cpf-input")).sendKeys("68015876842");
        driver.findElement(By.id("name-input")).sendKeys("Joana Gabrielly da Costa");
        driver.findElement(By.id("birthday-input")).sendKeys("22051996");
        driver.findElement(By.id("phone-input")).sendKeys("19997462400");

        WebElement element = driver.findElement(By.id("gender_F"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/form/button")).click();

        System.out.println("Finalizando teste.");
    }

}

