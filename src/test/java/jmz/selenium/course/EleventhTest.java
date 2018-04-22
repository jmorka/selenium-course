package jmz.selenium.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EleventhTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myEleventhTest() throws Exception {
        driver.get("http://localhost/litecart/en/create_account");

        WebElement form = driver.findElement(By.id("create-account"));

        WebElement firstname = form.findElement(By.name("firstname"));
        firstname.sendKeys("Jan");

        WebElement lastname = form.findElement(By.name("lastname"));
        lastname.sendKeys("Kowalski");

        WebElement address1 = form.findElement(By.name("address1"));
        address1.sendKeys("Testowa 234/9B");

        WebElement postcode = form.findElement(By.name("postcode"));
        postcode.sendKeys("01-248");

        WebElement city = form.findElement(By.name("city"));
        city.sendKeys("Warszawa");

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String userEmail = "test" + timeStamp + "@test.pl";
        WebElement email = form.findElement(By.name("email"));
        email.sendKeys(userEmail);

        WebElement phone = form.findElement(By.name("phone"));
        phone.sendKeys("+48111222333");

        WebElement password = form.findElement(By.name("password"));
        password.sendKeys("Test.123");

        WebElement confirmedPassword = form.findElement(By.name("confirmed_password"));
        confirmedPassword.sendKeys("Test.123");

        WebElement createAccount = form.findElement(By.cssSelector("button[name=\"create_account\"]"));
        createAccount.click();

        WebElement logout = driver.findElement(By.cssSelector("#box-account li:nth-child(4) a"));
        logout.click();

        WebElement emailAddress = driver.findElement(By.name("email"));
        emailAddress.sendKeys(userEmail);

        WebElement userPassword = driver.findElement(By.name("password"));
        userPassword.sendKeys("Test.123");

        WebElement login = driver.findElement(By.cssSelector("#box-account-login button[name=\"login\"]"));
        login.click();

        logout = driver.findElement(By.cssSelector("#box-account li:nth-child(4) a"));
        logout.click();
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
