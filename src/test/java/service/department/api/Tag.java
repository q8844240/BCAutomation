package service.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.Work;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Tag {

    public Response create(String tagName){

        HashMap<String,String> data = new HashMap<String,String>();
        data.put("tagname",tagName);

       return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/create")
                .then().log().all()
                .body("errcode",equalTo(0))
               .extract().response();
    }

    public Response list(){

        return given()
                .queryParam("access_token",Work.getInstance().getToken())
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/list")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response updata(Integer tagId,String tagName){

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("tagid",tagId);
        map.put("tagname",tagName);

        return given()
                .queryParam("access_token",Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/update")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response delete(int tagID){
        return given()
                .queryParam("access_token",Work.getInstance().getToken())
                .queryParam("tagid",tagID)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/tag/delete")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }

    public Response addTagusers(Integer tagId, ArrayList userlist,ArrayList partylist){

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("tagid",tagId);
        map.put("userlist",userlist);
        map.put("partylist",partylist);

        return  given()
                .queryParam("access_token",Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(map)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers")
                .then().log().all()
                .body("errcode",equalTo(0))
                .extract().response();

    }

}
