package app.page;


import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.HashMap;


public class SearchPage extends BasePage{

    private By inputBox=By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage search(String keyWord) throws IOException {
        HashMap<String,Object> data = new HashMap<>();
        data.put("keyword",keyWord);
        setParams(data);
        parseSteps("search");
        //App.driver.findElement(inputBox).sendKeys(keyWord);
        //click(By.id("name"));
        return this;
    }

    public Float getCurrentPrice() {

        parseSteps("getCurrentPrice");
        MobileElement el6 = (MobileElement) findElement(By.id("com.xueqiu.android:id/current_price"));
        //MobileElement el6 = (MobileElement) App.driver.findElementByAccessibilityId("current_price");
        return Float.valueOf(getResults().get("price").toString());

    }

    public App cancle(){
//        click(By.id("com.xueqiu.android:id/action_close"));
        parseSteps("/app/page/SearchPage.yaml","cancle");
        return new App();
    }

    public SearchPage select(){
        click(By.id("com.xueqiu.android:id/follow_btn"));
        return this;
    }
}
