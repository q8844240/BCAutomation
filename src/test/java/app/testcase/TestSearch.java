package app.testcase;


import app.page.App;
import app.page.SearchPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.naming.directory.SearchControls;
import java.io.IOException;
import java.lang.reflect.Array;
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
    public static Collection<Object[]> data() throws IOException {
//        return Arrays.asList(new Object[][] {
//                { "alibaba", 100f },
//                { "xiaomi", 8f },
//                { "jingdong", 33f }
//        });
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = "/"+TestSearch.class.getCanonicalName().replace(".","/")+".yaml";
        Object[][] demo = mapper.readValue(
                TestSearch.class.getResourceAsStream(path),
                Object[][].class
        );
        return Arrays.asList(demo);
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public Double price;


//
//    @ParameterizedTest
//    @ValueSource(strings = {"xiaomi","alibaba","jd"})
//    public void search(String ss){
//        assertThat(searchPage.search(ss).getCurrentPrice(),greaterThan(100f));
//    }

    @Test
    public void search() throws IOException {
        assertThat(searchPage.search(stock).getCurrentPrice(),greaterThan(price.floatValue()));
    }

    @After
    public void after(){
        searchPage.cancle();
    }




}
