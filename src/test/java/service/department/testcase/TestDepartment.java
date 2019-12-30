package service.department.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import service.department.api.Department;

import java.util.ArrayList;
import java.util.function.Consumer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TestDepartment {


    static Department department = new Department();

    @BeforeClass
    public static void beforeClass(){
        ArrayList<Integer> ids = department.list(department.parentDepartid).then()
                .extract().body().path("department.findAll{d->d.parentid==" + department.parentDepartid + "}.id");
        System.out.println(ids);
        ids.forEach(id->department.delete(id));
    }

    @Test
    public void list(){
        department.list(department.parentDepartid)
                .then().body("errmsg",equalTo("ok"));
    }

    @Test
    public void create(){
        String name = "种花兔3";
        department.deparCreate(name).then().body("errmsg",equalTo("created"));
        department.list(department.parentDepartid).then().body("department.id",hasSize(2));
    }

    @Test
    public void delete(){
        int id = department.deparCreate("种花兔5").then().body("errmsg",equalTo("created"))
                .extract().body().path("id");
        department.delete(id).then().body("errmsg",equalTo("deleted"));
    }
}
