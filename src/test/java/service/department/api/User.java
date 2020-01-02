package service.department.api;

import io.restassured.response.Response;
import service.Work;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class User {

    //获取部门成员
    public Response list(int department_id){

        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("department_id", department_id)
                .queryParam("fetch_child", 1)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

}
