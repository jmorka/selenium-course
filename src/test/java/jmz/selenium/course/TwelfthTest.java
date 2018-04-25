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

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TwelfthTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myTwelfthTest() throws Exception {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String productName = "product" + timeStamp;

        login();
        addProduct();
        fillGeneralTab(productName);
        fillInformationTab();
        fillPricesTab();
        checkCreation(productName);
    }

    private void login() {
        driver.get("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private void addProduct() {
        WebElement catalog = driver.findElement(By.cssSelector("#box-apps-menu li:nth-child(2)"));
        catalog.click();
        WebElement addNewProduct = driver.findElement(By.cssSelector("#content a:nth-child(2) "));
        addNewProduct.click();
    }

    private void fillGeneralTab(String productName) throws URISyntaxException {
        WebElement tab = driver.findElement(By.id("tab-general"));

        WebElement statusRadiobutton = tab.findElement(By.cssSelector("input[name=\"status\"][value=\"1\"]"));
        statusRadiobutton.click();


        WebElement name = tab.findElement(By.name("name[en]"));
        name.sendKeys(productName);

        WebElement code = tab.findElement(By.name("code"));
        code.sendKeys("111");

        WebElement category = tab.findElement(By.cssSelector("input[name=\"categories[]\"][value=\"1\"]"));
        category.click();

        WebElement defaultCategory = tab.findElement(By.name("default_category_id"));
        new Select(defaultCategory).selectByValue("1");

        WebElement productGroup = tab.findElement(By.cssSelector("input[name=\"product_groups[]\"][value=\"1-2\"]"));
        productGroup.click();

        WebElement quantity = tab.findElement(By.name("quantity"));
        quantity.sendKeys("2");

        WebElement soldOutStatus = tab.findElement(By.name("sold_out_status_id"));
        new Select(soldOutStatus).selectByValue("");

        WebElement uploadImage = tab.findElement(By.name("new_images[]"));
        URL resource = TwelfthTest.class.getClassLoader().getResource("fox.jpg");
        uploadImage.sendKeys(Paths.get(resource.toURI()).toFile().getAbsolutePath());

        WebElement dateValidFrom = tab.findElement(By.name("date_valid_from"));
        dateValidFrom.sendKeys("24042018");

        WebElement dateValidTo = tab.findElement(By.name("date_valid_to"));
        dateValidTo.sendKeys("24042019");
    }

    private void fillInformationTab() {
        WebElement information = driver.findElement(By.cssSelector("ul.index  li:nth-child(2) a:nth-child(1)"));
        information.click();

        WebElement tab = driver.findElement(By.id("tab-information"));

        WebElement manufacturer = tab.findElement(By.name("manufacturer_id"));
        new Select(manufacturer).selectByValue("1");

        WebElement keyword = tab.findElement(By.name("keywords"));
        keyword.sendKeys("test");

        WebElement shortDescription = tab.findElement(By.name("short_description[en]"));
        shortDescription.sendKeys("test short description");

        WebElement description = tab.findElement(By.cssSelector("div.trumbowyg-editor"));
        description.sendKeys("a test description of a new product");

        WebElement headTitle = tab.findElement(By.name("head_title[en]"));
        headTitle.sendKeys("test head title");

        WebElement metaDescription = tab.findElement(By.name("meta_description[en]"));
        metaDescription.sendKeys("test meta description");
    }


    private void fillPricesTab() {
        WebElement prices = driver.findElement(By.cssSelector("ul.index  li:nth-child(4) a:nth-child(1)"));
        prices.click();

        WebElement tab = driver.findElement(By.id("tab-prices"));

        WebElement purchasePrice = tab.findElement(By.name("purchase_price"));
        purchasePrice.sendKeys("2");

        WebElement purchasePriceCurrencyCode = tab.findElement(By.name("purchase_price_currency_code"));
        new Select(purchasePriceCurrencyCode).selectByValue("EUR");

        WebElement priceUsd = tab.findElement(By.name("prices[USD]"));
        priceUsd.sendKeys("1.5");

        WebElement priceEur = tab.findElement(By.name("prices[EUR]"));
        priceEur.sendKeys("2");

        WebElement save = driver.findElement(By.cssSelector(".button-set button:nth-child(1)"));
        save.click();
    }

    private void checkCreation(String productName) throws Exception {
        List<WebElement> productList = driver.findElements(By.cssSelector("form[name=\"catalog_form\"] tr.row td:nth-child(3) a"));
        List<String> productNamesList = productList.stream().map(g -> g.getAttribute("textContent")).collect(Collectors.toList());
        if (!productNamesList.contains(productName)) {
            throw new Exception("product is not in the list");
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
