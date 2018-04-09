package jmz.selenium.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SeventhTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mySeventhTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menuList = driver.findElements(By.id("app-"));
        int m = menuList.size();
        for (int i = 0; i < m; i++) {
            menuList = driver.findElements(By.id("app-"));
            menuList.get(i).click();
            driver.findElement(By.cssSelector("td#content h1"));
            List<WebElement> submenuList = driver.findElements(By.cssSelector("ul.docs li"));
            int s = submenuList.size();
            for (int y = 0; y < s; y++) {
                submenuList = driver.findElements(By.cssSelector("ul.docs li"));
                submenuList.get(y).click();
                driver.findElement(By.cssSelector("td#content h1"));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}