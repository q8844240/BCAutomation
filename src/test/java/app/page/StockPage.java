package app.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StockPage extends BasePage{

    public StockPage deletAll(){
        click(By.id("com.xueqiu.android:id/edit_group"));
        click(By.id("com.xueqiu.android:id/check_all"));
        click(By.id("com.xueqiu.android:id/cancel_follow"));
        click(By.id("com.xueqiu.android:id/md_buttonDefaultPositive"));
        //click(By.id("com.xueqiu.android:id/tv_right"));
        click(By.id("com.xueqiu.android:id/action_close"));
        return this;
    }

    public List<String> getAllStocks(){
        handleAlert();
        List<String> stocks = new ArrayList<>();
        for (WebElement element:driver.findElements(By.id("com.xueqiu.android:id/portfolio_stockName"))){
            stocks.add(element.getText());
        }
        System.out.println(stocks);
        return stocks;
    }

    public StockPage addDefaultSelectedStocks(){
        click(By.id("com.xueqiu.android:id/add_to_portfolio_stock"));
        return this;
    }

}
