import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","S10e");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","main.MainActivity");
        capabilities.setCapability("app","C:/Users/Home/Desktop/Automation/apks/org.wikipedia.apk");

        driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void myTest()
    {
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Поиск по Википедии')]");
        element_to_init_search.click();
        WebElement element_to_search_line = waitForElement("//*[contains (@text,'search_src_text')]" , "Cannot find input" , 10);
        element_to_search_line.sendKeys("java");
    }
    private WebElement waitForElement(String id,String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait=new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id("search_src_text");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
}
