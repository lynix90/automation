import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
        capabilities.setCapability("deviceName", "5x");
        capabilities.setCapability("platformVersion", "6.0.1");
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
    public void firstTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Can't find 'Search Wikipedia' input or click on it", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains (@text,'Search…')]"), "Java", "Can't find the search element or sendkeys", 5);

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search");
    }

    @Test
    public void TestCancelSearch() {
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
    public void testCompareArticleTitle() {
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
    public void homework2_1() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        WebElement search_element = waitForElementPresent(By.id("search_src_text"),
                "can't find the element of search",
                5);
        String searchText = search_element.getAttribute("text");
        Assert.assertEquals("The 'Search...' text isn't find", "Search…", searchText);
    }

    @Test
    public void homework2_2() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Opel",
                "Can't type 'Opel' or find the search field",
                5);
        waitForElementPresent(By.xpath("//*[@class='android.widget.LinearLayout']//*[@index='3'][@instance='13']"),
                "Can't find index 3 in the search results",
                5);
        waitForElementAndClear(By.id("search_src_text"),
                "Can't clear the search field",
                10);
        waitForElementNotPresent(By.id("page_list_item_container"),
                "There are one or more results of search",
                10);
    }
/*    @Test
    public void homework2_3()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);

    }
*/
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search",
                10);
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                10);
        swipeUp(2000); //чем больше тем медленнее свайп
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
    }

    @Test
    public void testSwipeToElement() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Can't type 'Appium' or find the search field",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                /* удаляем конструкцию //* (переход на более низкий уровень), так как мы будем искать
                текст прямо в элементе page_list_item_title
                 */
                "can't find and click on element of search",
                5);
        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),
                "Can't find the end of the article", 20);
    }
    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                8);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search",
                10);
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                10);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find or click Options button", 8);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list'][@instance='2']"),
                //Так тапаем по 3 пункту выпадающего меню (индекс), имеющему инстанс 2. без //* между параметрами, так как они находятся
                //в самом linearLayout (By.xpath("//*[@class='android.widget.LinearLayout'][@index='2'][@instance='2']")
                "Can't find or click 'Add to reading list' button", 8);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'got it' tip overlay", 8);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Can't find text input or clear it", 8);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                "Learning programming", "Can't find text input or type the name",
                8);
        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Can't find or click 'OK' button", 8);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find or click close button" ,8);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find or click My lists button" ,8);
        waitForElementAndClick(By.id("org.wikipedia:id/item_container"),
                "Can't find or click on saved reading list" ,10);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Can't swipe and delete element");
        waitForElementNotPresent(By.xpath("//@text='You have no articles added to this list.'"),
                "There's one or more saved article in list", 10);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until
                (
                        ExpectedConditions.presenceOfElementLocated(by)
                );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 10);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until
                (
                        ExpectedConditions.invisibilityOfElementLocated(by)
                );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return (element);
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver); //вызываем метод touchAction драйвера апиум
        Dimension size = driver.manage().window().getSize(); //передаем в переменную size с помощью метода
        //селениума размер экрана
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8); //с помощью (int) (выражение) происходит перевод double в int. дабл получили
        //потому что умножали на 0.8
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform(); //нажатие, ожидание след действия, сам свайп, отпускание и выполнение всех действий
    }
    protected void swipeElementToLeft(By by, String error_message)
    {
        waitForElementPresent(by, error_message, 10);
        WebElement element = waitForElementPresent(by, error_message, 10);
        //тут передали наш найденный локатор в переменную element
        int left_x = element.getLocation().getX();
        //Сюда запишется самая левая координата по оси Х
        int right_x = left_x + element.getSize().getWidth();
        //Берем крайнюю точку элемента по ширине и прибавляем к начальной точке left_x
        int upper_y = element.getLocation().getY();
        int lower_y =  upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        //верхняя точка+нижняя точка по оси у деленная на два = средняя точка y
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(150)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
    protected void swipeUpQuick() {
        swipeUp(200); //быстрый свайп
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        driver.findElements(by);//в отличие от findElement находит не один элемент на странице а все которые там есть
        driver.findElements(by).size(); //узнает количество элементов, найденных в findElements
        int alredy_swiped = 0;
        while (driver.findElements(by).size() == 0) //цикл работает пока функция не находит ни одного элемента, завершается по нахождении
        {
            if (alredy_swiped > max_swipes)
            {
                waitForElementPresent(by, "Can't find element by swiping up. \n" + error_message,0);
                //+error_message - передаем сюда помимо ошибки не нахождения элемента по свайпу еще и
                //error_message самого метода, где, например указываем конкретное место ненахождения
                //таймаут 0 так как мы уже сколько то раз просвайпали и ждать перед поиском элемента не надо
                return;
            }
            swipeUpQuick();
            ++alredy_swiped;
        }
        /* Метод, благодаря быстрому скроллу в цикле, ищет количество элементов не равное нулю (то есть ищет первый же
        подходящий элемент из переменной by)
        if (alredy_swiped > max_swipes) - В случае, если элемент на странице не был найден тест бы
        работал бесконечно и не падал без введения условия по ограничению max_swipes
        */
    }
}