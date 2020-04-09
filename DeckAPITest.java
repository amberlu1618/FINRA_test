package com.amberlu1618.FINRA_test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)

public class DeckAPITest {

    @Value("https://deckofcardsapi.com/api")
    private String REST_SERVICE_URI ;

      
    @Test
    public void getNewDeck(){
        when().
                get(REST_SERVICE_URI + "/deck/new/").peek().
        then().assertThat()
                .statusCode(200)
                .body("success", Matchers.equalTo("true"))
                .body("remaining",Matchers.equalTo(52));
    }

    @Test
    public void getACardDrawn(String deck_id){
        when().
                get(REST_SERVICE_URI + "/deck/<<" + deck_id + ">>/draw/?count=2").peek().
        then().assertThat()
                .statusCode(200)
                .body("success",Matchers.equalTo("true"));
    }


}