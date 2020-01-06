package framework;

import io.restassured.response.Response;

import java.util.HashMap;

public class ApiObjectModel {

    public HashMap<String,ApiObjectMethodModel> methods = new HashMap<>();
    public ApiObjectMethodModel getMethod(String method){
        return methods.get(method);
    }

    public Response run(String method){
        System.out.println(method);
        return null;
    }

}
