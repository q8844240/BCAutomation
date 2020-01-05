package service.user.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.Work;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

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

    public Response get(String userid){

        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("userid", userid)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().extract().response();
    }

    public Response updata(String userid, HashMap data){

        data.put("userid",userid);

        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then()
                .extract().response();


    }

    public Response create(String userid, HashMap<String, Object> data) {
        data.put("userid", userid);

        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .body(data)
                .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public Response clone(String userid, HashMap<String, Object> data) {
        data.put("userid", userid);

        //todo:使用模板技术
        String body = template("/service/user/api/user.json",data);

        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    public String template(String templatePath,HashMap<String, Object> data){

        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        ///Users/mac/IdeaProjects/BCAutomation/BCAutomation/src/main/resources/service/user/api/user.json
        Mustache mustache = mf.compile(this.getClass().getResource(templatePath).getPath());
        mustache.execute(writer, data);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();

    }

    public Response delete(String userid) {

        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("userid", userid)
                .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all().extract().response();
    }

}
