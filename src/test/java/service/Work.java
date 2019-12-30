package service;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Work {

    private static Work work;
    String token;

    public static Work getInstance(){

        if (work==null){
            work = new Work();
        }
        return work;
    }

    public String getToken(){
        if (token==null) {
            token = given().param("corpid", "ww823c10999f2a4d82")
                    .param("corpsecret", "24fck4YslCbwPMlj4Ew9CZWFcEGLwngRsZCQ1SC4SZo")
                    .when()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then().log().all()
                    .body("errcode", equalTo(0))
                    .extract().response().path("access_token");
        }

        return token;
    }

}
