package service.user.testcase;

import org.junit.jupiter.api.Test;
import service.user.api.User;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

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
    public void createFromTemplate(){

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

}
