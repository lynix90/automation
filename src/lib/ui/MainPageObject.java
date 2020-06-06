package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    /* Пишем конструктор класса, к которому
    и будут обращаться все наши тесты
     */
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until
                (
                        ExpectedConditions.presenceOfElementLocated(by)
                );
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 10);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until
                (
                        ExpectedConditions.invisibilityOfElementLocated(by)
                );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return (element);
    }

    public void swipeUp(int timeOfSwipe) {
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
    public void swipeElementToLeft(By by, String error_message)
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
                .waitAction(300) //здесь лучше 300мс, иначе на тормозных девайсах
                // свайп не отработает
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }
    public void swipeUpQuick() {
        swipeUp(200); //быстрый свайп
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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
    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);//функция list создает некий список с названием elements
        return elements.size(); //size() возвращает количество элементов, которые были найдены с помощью driver.findElements(by)
        //и записаны в elements(фактически мы возвращаем размер elements)
    }
    public void assertElementNotPresent(By by,String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        /*Если тест выдает хоть один результат поиска
        мы будем выдавать exception:
        */
        if (amount_of_elements>0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            //Преобразовали by в строковое значение и передаем значение элемента для вывода в сообщении юзеру
            throw new AssertionError(default_message + " " + error_message);
            //сама ошибка ассерта
        }
    }
    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by,error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
        //получаем атрибуты элемента. attribute - Значение атрибута, которое мы будем получать
    }
    public void assertElementPresent(By by, String error_message)
        {
            int amount_of_elements = getAmountOfElements(by);
            if (amount_of_elements<=0)
            {
                String default_message = "An element '" + by.toString() + "' supposed to be present";
                throw new AssertionError(default_message + " " + error_message);
            }
    }
    public void tapOptionsElementLowerPoint(By by, String error_message, long timeoutInSeconds)
    {
        waitForElementPresent(by, error_message, timeoutInSeconds);
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        int x = element.getLocation().getX();
        int y = (int)(element.getSize().getHeight()*0.95) ;
        TouchAction action = new TouchAction(driver);
        action.press(x, y).release().perform();
    }
}