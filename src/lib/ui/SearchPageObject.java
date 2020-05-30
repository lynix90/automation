package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains (@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    //Здесь заменили xpath текста на подстроку {SUBSTRING}, которую будем брать из нового метода getResultSearchElement
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver); //берем драйвер из MainPageObject
    }

    /*TEMPLATES Методы - это методы шаблонов, то есть в target мы заменяем какое то значение по шаблону на другое*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring); /*
         встроенная функция replace, в которой target - то, что будем заменять, а substring - наше значение, передаваемое в метод
         waitForSearchResult. Метод возвращает замененный xpath локатора SEARCH_RESULT
         */
    }

    /*TEMPLATES Методы*/
    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can't find and click Search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Can't find search input after clicking search init element", 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Can't find the search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can't find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can't find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) /* Сюда передаем substring для того,
чтобы можно было подставлять эту переменную вместо @text и сделать метод универсальным
*/ {
        String search_result_xpath = getResultSearchElement(substring);
    /*замененный с помощью метода getResultSearchElement
    xpath локатора SEARCH_RESULT мы записываем в новую переменную
     */
        this.waitForElementPresent(By.xpath(search_result_xpath), "Can't find search result with substring " + substring, 10);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
    /*замененный с помощью метода getResultSearchElement
    xpath локатора SEARCH_RESULT мы записываем в новую переменную
     */
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Can't find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "can't find anything by request ",
                15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
        /*Записали в переменную результат работы метода getAmountOfELements(передав ему xpath
         Теперь нужно убедиться что результатов больше нуля*/
    }
    public void waitForEmptyResultsLabel()
    {
       this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "can't find empty result element", 15);
        //Проверяем что элемент не представлен по search_result_locator
        //в котором у нас локатор одного из результатов поиска
    }
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "we found some results by request");
    }
}