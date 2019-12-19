package app.testcase;

import app.page.App;
import app.page.SearchPage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestSearch {

    public static SearchPage searchPage;

    @BeforeClass
    public void beforeAll() throws MalformedURLException {
        App.start();
        searchPage=App.toSearch();
    }

    @Test
    public void search() throws MalformedURLException {
        assertThat(searchPage.search("alibaba").getCurrentPrice(),equalTo(100));
    }
}
