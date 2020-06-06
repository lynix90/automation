package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        /*
        данную конструкцию пишем каждый раз, когда вызываем методы из других классов
        */
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals("Title isn't equals with expected",
                "Java (programming language)",
                article_title);
    }
    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        /*
        данную конструкцию пишем каждый раз, когда вызываем методы из других классов
        */
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
    /* @Test
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

                "can't find and click on element of search",
                5);
        MainPageObject.swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),
                "Can't find the end of the article", 20);
    } */
}
