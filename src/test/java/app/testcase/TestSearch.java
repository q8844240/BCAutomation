package app.testcase;

import app.page.App;
import app.page.SearchPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestSearch {

    public static SearchPage searchPage;

    @BeforeAll
    public void beforeAll() throws MalformedURLException {
        App.start();
        searchPage=App.toSearch();
    }

    @Test
    public void search() throws MalformedURLException {
        assertThat(searchPage.search("alibaba").getCurrentPrice(),equalTo(100));
    }
}
