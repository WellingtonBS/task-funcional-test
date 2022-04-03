package tasksFuncional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class taskTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Impulse\\Projetos\\DEVImpulse\\Automacao\\Driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8095/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

        /*
        //Define desired capabilities
        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);

        //Chrome option
        ChromeOptions options = new ChromeOptions();
        options.merge(cap);
        options.setHeadless(true);

        //Hub URL
        String huburl ="http://192.168.100.186:5555/wd/hub";

        // Create driver with hub address and capability
        WebDriver driver=new RemoteWebDriver(new URL(huburl), options);

        //Test case
        driver.get("http://192.168.100.186:8095/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
        Testes com Selenium */
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("TesteNew2");
            driver.findElement(By.id("dueDate")).sendKeys("31/12/2022");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("");
            driver.findElement(By.id("dueDate")).sendKeys("31/12/2021");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaSemData() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("TesteNew");
            driver.findElement(By.id("dueDate")).sendKeys("");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaComDataPassada() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("TesteNew");
            driver.findElement(By.id("dueDate")).sendKeys("31/01/2019");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", mensagem);
        } finally {
            driver.quit();
        }
    }
}
