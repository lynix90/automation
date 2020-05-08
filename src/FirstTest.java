import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
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
    public void firstTest()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),"Can't find 'Search Wikipedia' input or click on it", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains (@text,'Search…')]"), "Java", "Can't find the search element or sendkeys", 5);

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search");
        }
    @Test
    public void TestCancelSearch()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Can't find search field",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                        "Can't find close button or click on it",
                        5);
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "The close element is presented on this screen",
                5);
    }
    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search",
                5);
        WebElement title_element = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                10);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals("Title isn't equals with expected", "Java (programming language)", article_title);
    }
    @Test
    public void homework1()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        WebElement search_element = waitForElementPresent(By.id("search_src_text"),
                "can't find the element of search",
                5);
        String searchText = search_element.getAttribute("text");
        Assert.assertEquals("The 'Search...' text isn't find","Search…", searchText);
    }
/* Чет не работает, надо разобраться с xpath   @Test
    public void homework2()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Opel",
                "Can't type 'Opel' or find the search field",
                5);
        waitForElementPresent(By.xpath("//*[@id='org.wikipedia:id/page_list_item_container']//*[@instance='13']"),
                "Can't find index 3 in the search results",
                5);
        waitForElementAndClear(By.id("search_src_text"),
                "Can't clear the search field",
                10);
        waitForElementNotPresent(By.id("page_list_item_container"),
                "There are one or more results of search",
                10);
    } */
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until
                (
                        ExpectedConditions.presenceOfElementLocated(by)
                );
    }
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 10);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait=new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return (element);
    }
}