package tasksFuncional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class taskTest {

    public WebDriver acessarAplicacao() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Impulse\\Projetos\\DEVImpulse\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/tasks/");
        return driver;

    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("TesteNew");
            driver.findElement(By.id("dueDate")).sendKeys("31/12/2021");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!",mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("");
            driver.findElement(By.id("dueDate")).sendKeys("31/12/2021");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description",mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("TesteNew");
            driver.findElement(By.id("dueDate")).sendKeys("");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date",mensagem);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.findElement(By.xpath("//*[@id=\"addTodo\"]")).click();
            driver.findElement(By.id("task")).sendKeys("TesteNew");
            driver.findElement(By.id("dueDate")).sendKeys("31/01/2019");
            driver.findElement(By.id("saveButton")).click();

            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past",mensagem);
        } finally {
            driver.quit();
        }
    }
}
