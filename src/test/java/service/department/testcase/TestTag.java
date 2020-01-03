package service.department.testcase;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;
import service.department.api.Department;
import service.department.api.Tag;
import service.department.api.User;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTag {

    static Tag tag = new Tag();
    User user = new User();
    Department department = new Department();

    @BeforeAll
    public static void beforeAll(){
        ArrayList<Integer> list = tag.list().then().body("errmsg",equalTo("ok"))
                .extract().body().path("taglist.tagid");
        System.out.printf(list.toString());
        for (int i = 0; i < list.size(); i++) {
            tag.delete(list.get(i));
        }
    }

    //创建标签
    @Test
    @Order(1)
    public void createTest(){
        tag.create("活动组1").then().body("errmsg",equalTo("created"));
    }

   // 获取标签列表
    @Test
    @Order(5)
    public void listTest(){

        tag.list().then().body("errmsg",equalTo("ok"));
    }

    //删除标签
    @Test
    @Order(3)
    public void deleteTest(){
        int id = tag.create("新增").then().body("errmsg",equalTo("created"))
        .extract().body().path("tagid");
        tag.delete(id).then().body("errmsg",equalTo("deleted"));

    }

    //更新标签名字
    @Test
    @Order(2)
    public void updataTest(){
        int id = tag.create("原始").then().body("errmsg",equalTo("created"))
                .extract().body().path("tagid");
        tag.updata(id,"更新").then().body("errmsg",equalTo("updated"));
    }


    //增加标签成员
    @Test
    @Order(4)
    public void addTagusersTest(){

        Integer tagid =  tag.create("增加成员").then().body("errmsg",equalTo("created"))
                .extract().body().path("tagid");

        ArrayList userList = user.list(4).then().body("errmsg",equalTo("ok"))
                .extract().body().path("userlist.userid");

        //department.findAll{d->d.name==战士}.id,这个取值方式会报错，求帮助

//      ArrayList partyList = department.list(department.firstDepartid).then().body("errmsg",equalTo("ok"))
//                .extract().body().path("department.findAll{d->d.name==战士}.id");

        Integer  departmentID = department.list(department.firstDepartid).then().body("errmsg",equalTo("ok"))
                .extract().body().path("department[-2].id");
        ArrayList partyList = new ArrayList();
        partyList.add(departmentID);

        tag.addTagusers(tagid,userList,partyList).then().body("errmsg",equalTo("ok"));

    }

    //获取标签成员
    @Order(6)
    @Test
    public void getTagUsersTest(){
        Integer tagId = tag.list().then().body("errmsg",equalTo("ok"))
                .extract().body().path("taglist[0].tagid");

        tag.tagGetUsers(tagId).then().body("errmsg",equalTo("ok"));
    }

    //删除标签成员
    @Order(7)
    @Test
    public void deleteTagUsersTest(){

        Integer tagid =  tag.create("删除标签成员").then().body("errmsg",equalTo("created"))
                .extract().body().path("tagid");

        ArrayList userList = user.list(4).then().body("errmsg",equalTo("ok"))
                .extract().body().path("userlist.userid");

        //department.findAll{d->d.name==战士}.id,这个取值方式会报错，求帮助

//      ArrayList partyList = department.list(department.firstDepartid).then().body("errmsg",equalTo("ok"))
//                .extract().body().path("department.findAll{d->d.name==战士}.id");

        Integer  departmentID = department.list(department.firstDepartid).then().body("errmsg",equalTo("ok"))
                .extract().body().path("department[-2].id");
        ArrayList partyList = new ArrayList();
        partyList.add(departmentID);

        tag.addTagusers(tagid,userList,partyList).then().body("errmsg",equalTo("ok"));
        tag.deleteTagUsers(tagid,userList,null).then().body("errmsg",equalTo("deleted"));

    }
}
