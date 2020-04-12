package app.page;

import app.testcase.TestSearch;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static AndroidDriver<WebElement> driver;
    private PageObjectModel model = new PageObjectModel();
    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    private static HashMap<String,Object> params = new HashMap<>();

    public static HashMap<String, Object> getResults() {
        return results;
    }

    private static HashMap<String,Object> results = new HashMap<>();

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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

    public void parseSteps(){
        parseSteps(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    public void parseSteps(String method) {

        String path = "/"+ this.getClass().getCanonicalName().replace(".","/")+".yaml";
        parseSteps(path,method);
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//
//        TypeReference<HashMap<String, PageObjectModel>> typeRef = new TypeReference<HashMap<String, PageObjectModel>>(){};
//        try {
//            PageObjectModel model = mapper.readValue(
//                    TestSearch.class.getResourceAsStream(path),
//                    PageObjectModel.class
//            );
//            parseSteps(model);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public  void parseSteps(String path,String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        TypeReference<HashMap<String, PageObjectMethod>> typeRef = new TypeReference<HashMap<String, PageObjectMethod>>(){};
        try {
            model = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    PageObjectModel.class
            );
            parseSteps(model.methods.get(method));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseModel(PageObjectModel model){

    }

    private static void parseElements(){

    }

    private void parseSteps(PageObjectMethod steps){
        steps.getSteps().forEach(step->{
            WebElement element = null;
            String id = step.get("id");
            if (id!=null){
                element = driver.findElement(By.id(id));
            }else if (step.get("xpath")!=null){
                element = driver.findElement(By.xpath(step.get("xpath")));
            }else if (step.get("aid")!=null){
                element = driver.findElement(MobileBy.AccessibilityId(step.get("aid")));
            }else if (step.get("element")!=null){
                element = driver.findElement(model.elements.get(step.get("element")).getLocator());
            }

            String send = step.get("send");

            if (send!=null){
                for (Map.Entry<String,Object> kv:params.entrySet()
                ) {
                    String matcher = "${"+kv.getKey()+"}";
                    if (send.contains(matcher)) {
                        System.out.printf(String.valueOf(kv));
                        send = send.replace(matcher, kv.getValue().toString());
                    }
                }
                element.sendKeys(send);
            }else if (step.get("get")!=null){
                String attribute = element.getAttribute(step.get("get"));
                results.put(step.get("dump"),attribute);

            }else {
                element.click();
            }

        });
    }

}
