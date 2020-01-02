package service.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.Work;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Department {

    public int parentDepartid = 2;
    public int firstDepartid = 1;

    public Response list(int id){
       return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("id",id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response Create(String name,int parentid){

        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("name",name);
        data.put("parentid",parentid);

       return given()
                .queryParam("access_token",Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .body("errcode",equalTo(0))
        .extract().response();

    }

    public Response Create(String name){
        return Create(name,parentDepartid);
    }

    public Response delete(int id){

        return given()
                .queryParam("access_token",Work.getInstance().getToken())
                .queryParam("id",id)
        .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
        .then().log().all()
                .body("errcode",equalTo(0))
        .extract().response();


    }

}
