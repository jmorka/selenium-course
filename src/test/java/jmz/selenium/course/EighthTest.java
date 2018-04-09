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

public class EighthTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mySeventhTest() throws Exception {
        driver.get("http://localhost/litecart");
        List<WebElement> productList = driver.findElements(By.cssSelector("li.product"));
        for (WebElement foundProduct : productList) {
            List<WebElement> stickerList = foundProduct.findElements(By.cssSelector("div.sticker"));
            int s = stickerList.size();
            if (s != 1) {
                throw new Exception("invalid stickers");
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
