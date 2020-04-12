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

    private static App app;
    public static App getInstance(){
        if (app==null){
            app = new App();
        }
        return app;
    }

    public void start() throws MalformedURLException {
        System.out.printf("ceshi 开始");
        System.out.printf("udid="+System.getenv("UDID"));

        System.out.printf("ceshi 结束");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("udid","emulator-5554");
        desiredCapabilities.setCapability("platformName", "android");
        //desiredCapabilities.setCapability("deviceName", "emulator-5554");
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

    public  SearchPage toSearch() {
        //click(By.id("com.xueqiu.android:id/tv_search"));
        parseSteps("/app/page/app.yaml","toSearch");
        return new SearchPage();

    }

    public  StockPage toStocks(){
//        click(By.xpath("//*[contains(@resource-id,'tab_name') and @text='自选']"));
        parseSteps("/app/page/app.yaml","toStocks");
        return new StockPage();
    }
}
