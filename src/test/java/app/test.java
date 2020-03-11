package app;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

public class test {

    private static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        //desiredCapabilities.setCapability("deviceName", "1ca2e5260304");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity","view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset",false);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        URL remoteUrl = new URL("http://localhost:4724/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

    }

    @Test
    public void sampleTest() {
        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_search");
        el2.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement el3 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el4.sendKeys("阿里巴巴");
        MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el5.click();
        MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout");
        el6.click();
        //fdsfdsfdsfdsfdsfdsfsdf


    }

    @Test
    public void scroll() throws InterruptedException, IOException {
        Thread.sleep(6000);
        for (int i = 0; i <5 ; i++) {
            swipe(0.5,0.8,0.5,0.2);
            FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE).getCanonicalFile(),new File(i+".png"));
        }
        //driver.navigate().back();
    }

    public void swipe(Double startX,Double startY,Double endX,Double endY){
        Dimension size = driver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point((int) (size.width*startX), (int) (size.height*startY)));
        touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        touchAction.moveTo(PointOption.point((int) (size.width*endX), (int) (size.height*endY)));
        touchAction.release();
        touchAction.perform();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}


