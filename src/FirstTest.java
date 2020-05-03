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
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "S10");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "C:/Users/Home/Desktop/Automation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void myTest()
    {
        waitForElementByXpathAndClick("//*[contains(@text, 'Search Wikipedia')]","Can't find Search Wikipedia input or click on it", 5);

        waitForElementByXpathAndSendKeys("//*[contains (@text,'Search…')]", "Java", "Can't find the search element or sendkeys", 5);

        waitForElementByXpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "can't find and click on element of search");
        }

    private WebElement waitForElementByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until
                (
                        ExpectedConditions.presenceOfElementLocated(by)
                );
    }

    private WebElement waitForElementByXpath(String xpath, String error_message)
    {
        return waitForElementByXpath(xpath, error_message, 10);
    }
    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}