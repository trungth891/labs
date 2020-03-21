package pkg_one;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Function;

public class open_google_search {
    WebDriver driver = null;
    WebDriverManager wdm = null;

    @Test
    public void openGG() throws InterruptedException {

        wdm = WebDriverManager.chromedriver();
        wdm = wdm.version("80.0.3987.106");
        wdm.setup();
        ChromeOptions crOpts = new ChromeOptions();
        crOpts.addArguments("--disable-notifications");
        crOpts.addArguments("--start-maximized");
        crOpts.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(crOpts);
        driver.get("https://google.com");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));

        String epxectedTitle = "Google";
        String actualTitle = "aaa";

        actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle,epxectedTitle);

        By bySearchInput = By.name("q");
        By bySearchButton = By.name("btnK");
        WebElement searchInput = driver.findElement(bySearchInput);
        WebElement searchButton = driver.findElement(bySearchButton);

        String keyword = "new search";
        searchInput.sendKeys(keyword);

        String getKeyword = searchInput.getAttribute("value");

        Assert.assertEquals(getKeyword,keyword);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        wait.until(driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));

        Boolean containsKeyword = driver.getTitle().contains(keyword);
        Assert.assertTrue(containsKeyword);
    }

    public void waitForAjaxJQueryProcess() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) driver)
                            .executeScript(
                                    "return Ext.Ajax.isLoading() == false;"));
                    return ajaxIsComplete;
                }
            });
        } catch (Exception e) {

        }
    }
}
