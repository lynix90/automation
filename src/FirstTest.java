import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.applet.Main;

public class FirstTest extends CoreTestCase {
        /* Наследуем в FirstTest, убираем из first test
то, что вынесли в coretestcase, в том числе лишние импорты
/*
    Следующую конструкцию используем т.к
    перенесли все методы из firsttest в mainpageobject:
 */
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testHomework2_1() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        WebElement search_element = MainPageObject.waitForElementPresent(By.id("search_src_text"),
                "can't find the element of search",
                5);
        String searchText = search_element.getAttribute("text");
        assertEquals("The 'Search...' text isn't find", "Search…", searchText);
    }

    @Test
    public void testHomework2_2() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Opel",
                "Can't type 'Opel' or find the search field",
                5);
        MainPageObject.waitForElementPresent(By.xpath("//*[@class='android.widget.LinearLayout']//*[@index='3'][@instance='13']"),
                "Can't find index 3 in the search results",
                5);
        MainPageObject.waitForElementAndClear(By.id("search_src_text"),
                "Can't clear the search field",
                10);
        MainPageObject.waitForElementNotPresent(By.id("page_list_item_container"),
                "There are one or more results of search",
                10);
    }

    @Test
    public void testHomework3_1() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String first_search_value = "FLAC";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                first_search_value,
                "Can't type " + "first_search_value" + " or find the search field",
                8);
        String first_search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                "//*[@text='Reference software for the handling of FLAC data']";
        MainPageObject.waitForElementAndClick(By.xpath(first_search_result_locator),
                "Can't find or click on result of search",
                10);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                8);
        String options_button_locator = "//android.widget.ImageView[@content-desc='More options']";
        MainPageObject.waitForElementAndClick(By.xpath(options_button_locator),
                "Can't find the options button",
                10);
        String addtoreadinglist_button_locator = "//*[@text='Add to reading list'][@instance='2']";
        MainPageObject.waitForElementAndClick(By.xpath(addtoreadinglist_button_locator),
                "Can't find or click 'Add to reading list' button",
                8);
        String onboarding_button = "org.wikipedia:id/onboarding_button";
        MainPageObject.waitForElementAndClick(By.id(onboarding_button),
                "Can't find 'got it onboarding button' tip overlay",
                8);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Can't find text input or clear it",
                8);
        String name_of_folder = "First reading list";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't find text input or type the name",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Can't find or click 'OK' button",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find or click close button",
                8);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String second_search_value = "Direct Stream Digital";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                second_search_value,
                "Can't type " + "second_search_value" + " or find the search field",
                8);
        String second_search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                "//*[@text='System for digitally recreating audible signals']";
        MainPageObject.waitForElementAndClick(By.xpath(second_search_result_locator),
                "Can't find or click on result of second search",
                10);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                8);
        String second_search_1_article_title = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title text or get attribute 'text'",
                10);
        MainPageObject.waitForElementAndClick(By.xpath(options_button_locator),
                "Can't find the options button",
                10);
        MainPageObject.waitForElementAndClick(By.xpath(addtoreadinglist_button_locator),
                "Can't find or click 'Add to reading list' button",
                8);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_container"),
                "Can't find any reading lists",
                10);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find or click close button",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find or click My lists button",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder + "']"),
                "Can't find or click on saved reading list",
                10);
        MainPageObject.swipeElementToLeft(By.xpath("//*[@text='reference software for the handling of FLAC data']"),
                "Can't swipe and delete element");
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='system for digitally recreating audible signals']"),
                "Can't find or click on saved article", 10);
        String second_search_2_article_title = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title text or get attribute 'text'",
                10);
        assertEquals("Article title have been changed after deleting first article",
                second_search_1_article_title, second_search_2_article_title);
    }

    @Test
    public void testHomework3_2()
    {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String first_search_value = "FLAC";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                first_search_value,
                "Can't type " + "first_search_value" + " or find the search field",
                8);
        String first_search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                "//*[@text='Reference software for the handling of FLAC data']";
        MainPageObject.waitForElementAndClick(By.xpath(first_search_result_locator),
                "Can't find or click on result of search",
                10);
        String title_text_locator = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";
        MainPageObject.assertElementPresent(By.xpath(title_text_locator), "Can't find a title text");
    }

    }