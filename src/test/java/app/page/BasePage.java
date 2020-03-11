package app.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static AndroidDriver<WebElement> driver;

    public static WebElement findElement(By by){
        System.out.println(by);
        //todo:递归是更好的
        try {
            return driver.findElement(by);
        }catch (Exception e){
            handleAlert();
            return driver.findElement(by);
        }

    }

    public static void click(By by){
        System.out.println(by);
        //todo:递归是更好的
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            handleAlert();
            driver.findElement(by).click();
        }

    }

    static void handleAlert() {
        By tips = By.id("com.xueqiu.android:id/snb_tip_text");
        //todo:不需要所有的都判断是否存在
        List<By> alertBox = new ArrayList<>();
        alertBox.add(By.id("com.xueqiu.android:id/image_cancel"));
        alertBox.add(By.id("com.xueqiu.android:id/ib_close"));
        alertBox.add(tips);
        alertBox.add(By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));


        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        for (int i = 0; i < alertBox.size(); i++) {
            By adsLocator = alertBox.get(i);
            List<WebElement> ads = driver.findElements(adsLocator);
            if (ads.size() >= 1) {
                ads.get(0).click();
            }
            if (adsLocator.equals(tips)){
                System.out.printf("snb_tip found");
                Dimension size = driver.manage().window().getSize();
                try {
                    if (driver.findElements(tips).size() >= 1){
                        new TouchAction(driver).tap(PointOption.point(size.width/2,size.height/2)).perform();
                    }
                }catch (Exception exception){

                }finally {
                    System.out.printf("", "snb_tip click");
                }
            }else if (ads.size() >= 1){
                ads.get(0).click();
            }
        }
    }

    private static void handleAlertByPageSource(){
        //todo:xpath匹配，标记 定位
        String xml = driver.getPageSource();
        List<String> alertBoxs = new ArrayList<>();
        alertBoxs.add("xxx");
        alertBoxs.add("yyy");

        alertBoxs.forEach(alert ->{
            if (xml.contains(alert)) {
                driver.findElement(By.id(alert)).click();
            }
        });
    }
}
