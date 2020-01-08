package framework;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import service.Work;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiObjectMethodModel {
    public HashMap<String,Object> params;
    public HashMap<String,Object> query;
    public HashMap<String,Object> header;
    public HashMap<String,Object> postBody;
    public String postBodyRaw;
    public String method="get";
    public String url = "";

    public Response run(){
        RequestSpecification given = given();

        given.queryParam("access_token", Work.getInstance().getToken());

        if (query != null) {
            query.entrySet().forEach(entry->{
                given.queryParam(entry.getKey(),repalce(entry.getValue().toString()));
            } );
        }

        if (header != null) {
            header.entrySet().forEach(entry->{
                given.header(entry.getKey(),repalce(entry.getValue().toString()));
            } );
        }

        if (postBody != null){
            given.body(postBody);
        }

        if (postBodyRaw != null){
            given.body(repalce(postBodyRaw));
        }

        return given.when().log().all().request(method,url)
                .then().log().all().extract().response();
    }

    public String repalce(String raw){


        for (Map.Entry<String,Object> kv : params.entrySet()) {
            String matcher = "${" + kv.getKey() + "}";
            if(raw.contains(matcher)){
                System.out.println("raw="+raw);
                System.out.println("macher="+matcher);
                raw = raw.replace(matcher,kv.getValue().toString());

            }
        }
        return raw;
    }

    public Response run(HashMap<String, Object> params) {
        this.params = params;
        return run();
    }
}
