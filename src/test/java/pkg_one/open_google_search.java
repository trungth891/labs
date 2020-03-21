package pkg_one;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class open_google_search {

    @BeforeTest
    public void setUp(){
    }

    @Test
    public void openGG(){
        WebDriverManager wdm = null;
        wdm = WebDriverManager.chromedriver();
        wdm = wdm.version("80.0.3987.106");
        wdm.setup();
        ChromeOptions crOpts = new ChromeOptions();
        crOpts.addArguments("--disable-notifications");
        crOpts.setPageLoadStrategy(PageLoadStrategy.NONE);
        WebDriver webdriver = new ChromeDriver(crOpts);
    }
}
