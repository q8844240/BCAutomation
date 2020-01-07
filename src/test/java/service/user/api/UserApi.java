package service.user.api;

import io.restassured.response.Response;

import java.util.HashMap;

public class UserApi extends BaseApi {

    public Response get(String userid){

        HashMap<String,Object> params = new HashMap<>();
        params.put("userid",userid);

        return parseSteps();
    }

}
