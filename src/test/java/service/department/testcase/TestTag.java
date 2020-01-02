package service.department.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import service.department.api.Department;
import service.department.api.Tag;
import service.department.api.User;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

public class TestTag {

    static Tag tag = new Tag();
    User user = new User();
    Department department = new Department();

//    @BeforeClass
//    public static void beforeAll(){
//        ArrayList<Integer> list = tag.list().then().body("errmsg",equalTo("ok"))
//                .extract().body().path("taglist.tagid");
//        System.out.printf(list.toString());
//        for (int i = 0; i < list.size(); i++) {
//            tag.delete(list.get(i));
//        }
//    }

    //创建标签
    @Test
    public void createTest(){
        tag.create("活动组").then().body("errmsg",equalTo("created"));
    }

   // 获取标签列表
    @Test
    public void listTest(){

        tag.list().then().body("errmsg",equalTo("ok"));
    }

    //删除标签
    @Test
    public void deleteTest(){
        int id = tag.create("新增").then().body("errmsg",equalTo("created"))
        .extract().body().path("tagid");
        tag.delete(id).then().body("errmsg",equalTo("deleted"));

    }

    //更新标签名字
    @Test
    public void updataTest(){
        int id = tag.create("原始").then().body("errmsg",equalTo("created"))
                .extract().body().path("tagid");
        tag.updata(id,"更新").then().body("errmsg",equalTo("updated"));
    }

    @Test
    public void addTagusersTest(){


        ArrayList userList = user.list(4).then().body("errmsg",equalTo("ok"))
                .extract().body().path("userlist.userid");

        //department.findAll{d->d.name==战士}.id,这个取值方式竟然会报错，求帮助

//      ArrayList partyList = department.list(department.firstDepartid).then().body("errmsg",equalTo("ok"))
//                .extract().body().path("department.findAll{d->d.name==战士}.id");

        Integer  departmentID = department.list(department.firstDepartid).then().body("errmsg",equalTo("ok"))
                .extract().body().path("department[-2].id");
        ArrayList partyList = new ArrayList();
        partyList.add(departmentID);

        tag.addTagusers(1,userList,partyList).then().body("errmsg",equalTo("ok"));


    }

}
