import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import sun.applet.Main;

public class FirstTest extends CoreTestCase
        /* Наследуем в FirstTest, убираем из first test
то, что вынесли в coretestcase, в том числе лишние импорты
 */ {
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
    public void testSearch()
    /*
    Переименовали тест. Тест должен начинаться со слова
    test, чтобы junit видел тест
     */ {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Can't find 'Search Wikipedia' input or click on it", 5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains (@text,'Search…')]"), "Java", "Can't find the search element or sendkeys", 5);

        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search");
    }

    @Test
    public void testCancelSearch() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Can't find search field",
                5);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Can't find close button or click on it",
                5);
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "The close element is presented on this screen",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search",
                5);
        WebElement title_element = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                10);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals("Title isn't equals with expected", "Java (programming language)", article_title);
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
        Assert.assertEquals("The 'Search...' text isn't find", "Search…", searchText);
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

    /*  Может быть доделаю?
        @Test
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
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search",
                10);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                10);
        MainPageObject.swipeUp(2000); //чем больше тем медленнее свайп
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);
        MainPageObject.swipeUp(2000);
    }

    @Test
    public void testSwipeToElement() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Can't type 'Appium' or find the search field",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                /* удаляем конструкцию //* (переход на более низкий уровень), так как мы будем искать
                текст прямо в элементе page_list_item_title
                 */
                "can't find and click on element of search",
                5);
        MainPageObject.swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),
                "Can't find the end of the article", 20);
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't type 'Java' or find the search field",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find and click on element of search",
                10);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find article title",
                10);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find or click Options button", 8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list'][@instance='2']"),
                //Так тапаем по 3 пункту выпадающего меню (индекс), имеющему инстанс 2. без //* между параметрами, так как они находятся
                //в самом linearLayout (By.xpath("//*[@class='android.widget.LinearLayout'][@index='2'][@instance='2']")
                "Can't find or click 'Add to reading list' button", 8);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Can't find 'got it' tip overlay", 8);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Can't find text input or clear it", 8);
        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder, "Can't find text input or type the name",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Can't find or click 'OK' button", 8);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find or click close button", 8);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find or click My lists button", 8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder + "']"),
                //Использована конкатинация строк (в ' ' кавычки добавляется еще
                //структура " + имя строковой переменной + ". Так можно использовать в
                //xpath переменные вместо значения
                "Can't find or click on saved reading list", 10);
        MainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Can't swipe and delete element");
        MainPageObject.waitForElementNotPresent(By.xpath("//@text='You have no articles added to this list.'"),
                "There's one or more saved article in list", 10);
    }

    @Test

    public void testAmountOfNotEmptySearch() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String search_line = "meteora album";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Can't enter the search line or find it",
                8);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        MainPageObject.waitForElementPresent(By.xpath(search_result_locator),
                "can't find anything by request " + search_line,
                15);
        int amount_of_search_results = MainPageObject.getAmountOfElements(By.xpath(search_result_locator));
        /*Записали в переменную результат работы метода getAmountOfELements(передав ему xpath
         Теперь нужно убедиться что результатов больше нуля*/
        Assert.assertTrue("We found too few results!", amount_of_search_results > 0);
        // в condition пишем условие успеха ассерта
    }

    @Test

    public void testAmountOfEmptySearch() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String search_line = "ktxktc";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Can't enter the search line or find it",
                8);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']" +
                "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";
        MainPageObject.waitForElementPresent(By.xpath(empty_result_label),
                "can't find empty result label by request: " + search_line,
                15);
        //Проверяем что элемент не представлен по search_result_locator
        //в котором у нас локатор одного из результатов поиска
        MainPageObject.assertElementNotPresent(By.xpath(search_result_locator), "we found some results by request" + search_line);
    }

    @Test
    public void testChangeOrientationOnSearchResults() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Can't enter the search line or find it",
                8);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find 'Object-oriented programming language' topic by" + search_line,
                10);
        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text", "Can't find title of article", 15);
        driver.rotate(ScreenOrientation.LANDSCAPE); //повернули экран с помощью встроенного метода
        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text", "Can't find title of article", 15);
            /* Мы будем сравнивать title_before и title_after_rotation, то есть сам текст заголовка
            Если они совпадают - тест прошел, иначе - тест падает
             */
        Assert.assertEquals("Article title have been changed after rotation", title_before_rotation,
                title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"),
                "text", "Can't find title of article", 15);
        Assert.assertEquals("Article title have been changed after rotation", title_after_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input or click on it",
                8);
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Can't enter the search line or find it",
                8);
        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find 'Object-oriented programming language' topic by" + search_line,
                10);
        driver.runAppInBackground(5);
        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "can't find after returning from background 'Object-oriented programming language' topic by" + search_line,
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
        Assert.assertEquals("Article title have been changed after deleting first article",
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