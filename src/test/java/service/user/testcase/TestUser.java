package service.user.testcase;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.jupiter.api.Test;
import service.user.api.User;

import java.io.*;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TestUser {

    @Test
    public void getTest(){

        User user = new User();
        user.get("1234").then()
                .body("name",equalTo("刀锋1"));

    }

    @Test
    public void updataTest(){

        User user = new User();
        String userId = "1234";
        String newName = "name for testing ";
        HashMap data = new HashMap();
        data.put("name",newName);
        data.put("address","address for testing");
        user.updata(userId,data);

        user.get(userId).then()
                .body("name",equalTo(newName));

    }

    @Test
    public void creatTest(){

        String userId = "zht"+System.currentTimeMillis();
        String newName = "name for testing1";
        HashMap data = new HashMap();
        data.put("name",newName);
        data.put("address","address for testing");
        data.put("department",new int[]{1});
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        User user= new User();

        user.create(userId,data).then().body("errcode",equalTo(0));
        user.get(userId).then().body("name",equalTo(newName));
    }

    @Test
    public void createFromTemplate() throws IOException {

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Mustache");
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        data.put("userid",System.currentTimeMillis());

        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        ///Users/mac/IdeaProjects/BCAutomation/BCAutomation/src/main/resources/service/user/api/user.json
        Mustache mustache = mf.compile(this.getClass().getResource("/service/user/api/user.json").getPath());
        mustache.execute(writer, data);
        writer.flush();
        System.out.println(writer.toString());
    }

    @Test
    public void cloneUser(){

        String userId = "zhm"+System.currentTimeMillis();
        String newName = "name for testing1";
        HashMap data = new HashMap();
        data.put("name",newName);
        data.put("address","address for testing");
        //data.put("department",new int[]{1});
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        User user= new User();

        user.clone(userId,data).then().body("errcode",equalTo(0));
        user.get(userId).then().body("name",equalTo(newName));
    }

    @Test
    public void delete(){

        String userId = "zhy"+System.currentTimeMillis();
        String newName = "name for testing1";
        HashMap data = new HashMap();
        data.put("name",newName);
        data.put("address","address for testing");
        //data.put("department",new int[]{1});
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        User user= new User();

        user.clone(userId,data).then().body("errcode",equalTo(0));
        //user.get(userId).then().body("name",equalTo(newName));
        user.delete(userId).then().body("errmsg",equalTo("deleted"));
        user.get(userId).then().body("errcode",not(equalTo(newName)));
    }

}
