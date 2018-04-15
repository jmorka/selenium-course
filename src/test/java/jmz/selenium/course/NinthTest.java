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
import java.util.stream.Collectors;

public class NinthTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myNinthPoint1Test() throws Exception {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> countriesList = driver.findElements(By.cssSelector("form[name=countries_form] tr.row td:nth-child(5)"));
        List<String> countryNamesList = countriesList.stream().map(c -> c.getAttribute("textContent")).collect(Collectors.toList());
        boolean isSorted = countryNamesList.stream().sorted().collect(Collectors.toList()).equals(countryNamesList);
        if (!isSorted) {
            throw new Exception("list is not sorted alphabetically");
        }

        List<WebElement> rowsList = driver.findElements(By.cssSelector("form[name=countries_form] tr.row"));
        int r = rowsList.size();
        for (int i = 0; i < r; i++) {
            WebElement row = rowsList.get(i);
            WebElement zonesNumber = row.findElement(By.cssSelector("td:nth-child(6)"));
            if (!zonesNumber.getAttribute("textContent").equals("0")) {
                WebElement countryLink = row.findElement(By.cssSelector(" td:nth-child(5) a"));
                countryLink.click();
                List<WebElement> zonesList = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) input[type=\"hidden\"]"));
                List<String> zonesNamesList = zonesList.stream().map(c -> c.getAttribute("value")).collect(Collectors.toList());
                boolean isZonesSorted = zonesNamesList.stream().sorted().collect(Collectors.toList()).equals(zonesNamesList);
                if (!isZonesSorted) {
                    throw new Exception("Zones list is not sorted alphabetically");
                }
                driver.navigate().back();
                rowsList = driver.findElements(By.cssSelector("form[name=countries_form] tr.row"));
            }
        }


    }

    @Test
    public void myNinthPoint2Test() throws Exception {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> geoList = driver.findElements(By.cssSelector("form[name=geo_zones_form] tr.row  td:nth-child(3) a"));
        int r = geoList.size();
        for (int i = 0; i < r; i++) {
            WebElement geo = geoList.get(i);
            geo.click();
            List<WebElement> geoCountryList = driver.findElements(By.cssSelector("#table-zones td:nth-child(2) option[selected=\"selected\"]"));
            List<String> geoCountryNamesList = geoCountryList.stream().map(g -> g.getAttribute("textContent")).collect(Collectors.toList());
            boolean isGeoCountrySorted = geoCountryNamesList.stream().sorted().collect(Collectors.toList()).equals(geoCountryNamesList);
            if (!isGeoCountrySorted) {
                throw new Exception("Geo zones list in not sorted alphabetically");
            }
            List<WebElement> geoZonesList = driver.findElements(By.cssSelector("#table-zones td:nth-child(3) option[selected=\"selected\"]"));
            List<String> geoZoneNamesList = geoZonesList.stream().map(g -> g.getAttribute("textContent")).collect(Collectors.toList());
            boolean isGeoZonesSorted = geoZoneNamesList.stream().sorted().collect(Collectors.toList()).equals(geoZoneNamesList);
            if (!isGeoZonesSorted) {
                throw new Exception("Geo zones list in not sorted alphabetically");
            }
            driver.navigate().back();
            geoList = driver.findElements(By.cssSelector("form[name=geo_zones_form] tr.row  td:nth-child(3) a"));

        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
