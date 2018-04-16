package jmz.selenium.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class TenthTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myTenthTest() throws Exception {
        driver.get("http://localhost/litecart");
        WebElement campaignProduct = driver.findElement(By.cssSelector("#box-campaigns  li.product a.link"));

        String productName = campaignProduct.findElement(By.cssSelector("div.name")).getAttribute("textContent");

        WebElement regularPrice = campaignProduct.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String regularPriceText = regularPrice.getAttribute("textContent");
        String regularPriceStyleColor = regularPrice.getCssValue("color");
        String regularPriceStyleTextDecorationLine = regularPrice.getCssValue("text-decoration-line");

        WebElement campaignPrice = campaignProduct.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String campaignPriceText = campaignPrice.getAttribute("textContent");
        String campaignPriceStyleColor = campaignPrice.getCssValue("color");
        String campaignPriceStyleFontWeight = campaignPrice.getCssValue("font-weight");

        campaignProduct.click();

        String productNameOnPage = driver.findElement(By.cssSelector("div#box-product h1.title")).getAttribute("textContent");

        WebElement regularPriceOnPage = driver.findElement(By.cssSelector("div#box-product div.information div.price-wrapper s.regular-price"));
        String regularPriceOnPageText = regularPriceOnPage.getAttribute("textContent");
        String regularPriceStyleOnPageColor = regularPriceOnPage.getCssValue("color");
        String regularPriceStyleOnPageTextDecorationLine = regularPriceOnPage.getCssValue("text-decoration-line");

        WebElement campaignPriceOnPage = driver.findElement(By.cssSelector("div#box-product div.information div.price-wrapper strong.campaign-price"));
        String campaignPriceOnPageText = campaignPriceOnPage.getAttribute("textContent");
        String campaignPriceStyleOnPageColor = campaignPriceOnPage.getCssValue("color");
        String campaignPriceStyleOnPageFontWeight = campaignPriceOnPage.getCssValue("font-weight");

        assertEquals(productName, productNameOnPage);
        assertEquals(regularPriceText, regularPriceOnPageText);
        assertEquals(regularPriceStyleColor, regularPriceStyleOnPageColor);
        assertEquals(regularPriceStyleTextDecorationLine, regularPriceStyleOnPageTextDecorationLine);
        assertEquals(campaignPriceText, campaignPriceOnPageText);
        assertEquals(campaignPriceStyleColor, campaignPriceStyleOnPageColor);
        assertEquals(campaignPriceStyleFontWeight, campaignPriceStyleOnPageFontWeight);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
