package app.testcase;

import app.page.App;
import app.page.StockPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStock {
    private static StockPage stockPage;
    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        App.start();
        stockPage = App.toStocks();
    }

    @BeforeEach
    public void beforeEach(){

    }

    @Order(1)
    @Test
    public void addDefaultSelectedStocks(){

        if (stockPage.getAllStocks().size() >= 1){
            stockPage.deletAll();
        }
        assertThat(stockPage.addDefaultSelectedStocks().getAllStocks().size(),greaterThanOrEqualTo(6));
    }

    public static Stream<Arguments> StringProvider(){
        return Stream.of(
                arguments("pdd","拼多多"),
                arguments("tx","中兴通讯")
        );
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("StringProvider")
    public void addStock(String code,String name) throws IOException {
        stockPage.toSearch().search(code).select().cancle();

        assertThat(stockPage.getAllStocks(),hasItem(name));
    }


}
