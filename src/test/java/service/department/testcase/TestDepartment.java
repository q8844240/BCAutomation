package service.department.testcase;

import org.junit.Test;
import service.department.api.Department;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestDepartment {


    static Department department = new Department();
    @Test
    public void list(){
        department.list(department.parentDepartid)
                .then().body("errmsg",equalTo("ok"));
    }

    @Test
    public void create(){
        department.deparCreate("种花兔4").then().body("errmsg",equalTo("created"));
    }

    @Test
    public void delete(){
        int id = department.deparCreate("种花兔5").then().body("errmsg",equalTo("created"))
                .extract().body().path("id");
        department.delete(id).then().body("errmsg",equalTo("deleted"));
    }
}
