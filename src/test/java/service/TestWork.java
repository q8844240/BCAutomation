package service;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestWork {

    public static String token;
    public static int parentDepartid = 2;

    @Before
    public void getToken(){

        token =  given().param("corpid","ww823c10999f2a4d82")
                .param("corpsecret","24fck4YslCbwPMlj4Ew9CZWFcEGLwngRsZCQ1SC4SZo")
        .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
        .then().log().all()
                .body("errcode",equalTo(0))
        .extract().response().path("access_token");

        System.out.println(token);
    }

    @Test
    public void deparCreate(){

        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("name","种花兔1");
        data.put("parentid",parentDepartid);

        given()
                .queryParam("access_token",token)
                .contentType(ContentType.JSON)
                .body(data)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .body("errcode",equalTo(0));

    }

}
