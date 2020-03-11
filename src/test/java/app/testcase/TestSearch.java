package app.testcase;


import app.page.App;
import app.page.SearchPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.naming.directory.SearchControls;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(Parameterized.class)
public class TestSearch {

    public static SearchPage searchPage;

    @BeforeClass
    public static void beforeAll() throws MalformedURLException {
        App.start();
        //searchPage = App.toSearch();
    }
    @Before
    public void before(){
        searchPage = App.toSearch();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                { "alibaba", 100f },
                { "xiaomi", 8f },
                { "jingdong", 33f }
        });
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Float price;


//
//    @ParameterizedTest
//    @ValueSource(strings = {"xiaomi","alibaba","jd"})
//    public void search(String ss){
//        assertThat(searchPage.search(ss).getCurrentPrice(),greaterThan(100f));
//    }

    @Test
    public void search(){
        assertThat(searchPage.search(stock).getCurrentPrice(),greaterThan(price));
    }

    @After
    public void after(){
        searchPage.cancle();
    }




}
