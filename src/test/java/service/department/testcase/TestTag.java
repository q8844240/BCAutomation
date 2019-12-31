package service.department.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import service.department.api.Tag;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

public class TestTag {

    static Tag tag = new Tag();

    @BeforeClass
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



}
