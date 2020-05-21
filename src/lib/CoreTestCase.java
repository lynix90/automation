package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase
/* extends TestCase - наследуем в CoreTestCase
 и будем использовать класс TestCase из junit в котором
 много удобных методов. Убираем импорты от аннотаций
 Before и After, так же убрали их использование в коде вообще
 они есть в TestCase
 */
{
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override //указываем что мы перегружаем родительский метод setUp
    protected void setUp() throws Exception {
        /* Указываем, что мы юзаем метод
        setUp из TestCase
         */
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "mia1");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "C:/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    }

   @Override
    protected void tearDown() throws Exception
    // добавили обработку ексепшна, который возникал
    //при вызове tearDown
     {
         driver.rotate(ScreenOrientation.PORTRAIT);
         /* добавил в рамках задания 3 из раздела сложные тесты.
         Чтобы в случае падения после поворота экрана, следующий тест стартовал в
         портретной ориентации
         */
         driver.quit();
         super.tearDown();
     //с помощью super.вызываем те же методы, которые
     //вызывали в @before и@after,но теперь
     //вызываем их из пакета junit TestCase
 }
}
