package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

//Это "Конструктор"
public class MyListsPageObject extends MainPageObject {
    public static final String
    FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    public MyListsPageObject (AppiumDriver driver) {
        super(driver);
    }
    public void openFolderByName (String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        waitForElementPresent(By.xpath(folder_name_xpath),"Can't find folder by name",7);
        this.waitForElementAndClick(By.xpath(folder_name_xpath),
                //Использована конкатинация строк (в ' ' кавычки добавляется еще
                //структура " + имя строковой переменной + ". Так можно использовать в
                //xpath переменные вместо значения
                "Can't open folder by name " + name_of_folder, 10);
    }
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath),
                "Saved article aren't present with title" + article_title, 10);
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),
                "Saved article still present with title" + article_title, 10);
    }
    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath),
                "Can't swipe and delete element");
        this.waitForArticleToDisappearByTitle (article_title);
    }
    public void openArticleByName(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(By.xpath(article_xpath),"Can't find or click on saved article", 10);
    }
}
