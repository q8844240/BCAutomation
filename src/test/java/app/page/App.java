package app.page;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App extends BasePage {


    public static void start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        //desiredCapabilities.setCapability("deviceName", "1ca2e5260304");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity","view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        new WebDriverWait(driver,30)
                .until(x -> {
                    String xml = driver.getPageSource();
                    Boolean exist = xml.contains("home_search") || xml.contains("image_cancel");
                    System.out.println(exist);
                    return exist;
                });

//        new WebDriverWait(driver,30)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.id("com.xueqiu.android:id/home_search")));
    }

    public static SearchPage toSearch() {

        click(By.id("com.xueqiu.android:id/tv_search"));
        return new SearchPage();

    }

    public static StockPage toStocks(){
        click(By.xpath("//*[contains(@resource-id,'tab_name') and @text='自选']"));
        return new StockPage();
    }
}
