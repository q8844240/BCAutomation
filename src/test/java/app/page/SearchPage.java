package app.page;


import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;



public class SearchPage extends BasePage{

    private By inputBox=By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage search(String keyWord){

        App.driver.findElement(inputBox).sendKeys(keyWord);
        click(By.id("name"));
        return this;
    }

    public Float getCurrentPrice() {


        MobileElement el6 = (MobileElement) findElement(By.id("com.xueqiu.android:id/current_price"));
        //MobileElement el6 = (MobileElement) App.driver.findElementByAccessibilityId("current_price");
        return Float.valueOf(el6.getText());

    }

    public App cancle(){
        click(By.id("com.xueqiu.android:id/action_close"));
        return new App();
    }

    public SearchPage select(){
        click(By.id("com.xueqiu.android:id/follow_btn"));
        return this;
    }
}
