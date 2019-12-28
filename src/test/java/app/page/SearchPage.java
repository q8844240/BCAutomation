package app.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class SearchPage {

    private By inputBox = By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage search(String keyWord){

        App.driver.findElement(inputBox).sendKeys(keyWord);
        MobileElement el5 = (MobileElement) App.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el5.click();
        return this;
    }

    public Integer getCurrentPrice(){

        MobileElement el6 = (MobileElement) App.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout");
        el6.click();
        return Integer.valueOf(el6.getText());
        //测试
    }
}
