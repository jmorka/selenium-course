package jmz.selenium.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class ThirteenthTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myThirteenthTest() throws Exception {
        for (int i = 1; i <= 3; i++) {
            driver.get("http://localhost/litecart");
            WebElement firstProduct = driver.findElement(By.cssSelector("#box-latest-products li:nth-child(" + i + ") a"));
            firstProduct.click();
            List<WebElement> sizeSelectList = driver.findElements(By.cssSelector("#box-product div.buy_now form td.options select[name=\"options[Size]\"]"));
            if (sizeSelectList.size() > 0) {
                WebElement sizeSelect = sizeSelectList.get(0);
                new Select(sizeSelect).selectByValue("Small");
            }
            WebElement addToCartButton = driver.findElement(By.cssSelector("#box-product div.buy_now button"));
            addToCartButton.click();
            wait.until(textToBe(By.cssSelector("#cart a.content span.quantity"), String.valueOf(i)));
        }

        WebElement checkoutLink = driver.findElement(By.cssSelector("#cart a.link"));
        checkoutLink.click();


        for (int j = 1; j <= 3; j++) {
            WebElement removeButton = driver.findElement(By.cssSelector("#box-checkout-cart form button[value=\"Remove\"]"));
            WebElement orderSummaryRow = driver.findElement(By.cssSelector("#order_confirmation-wrapper table tbody tr:nth-child(2)"));
            removeButton.click();
            wait.until(stalenessOf(orderSummaryRow));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
