package service.user.testcase;

import org.junit.jupiter.api.Test;
import service.user.api.UserApi;

public class TestUserApi {

    @Test
    public void get(){

        UserApi user = new UserApi();
        user.get("userid");

    }

}
