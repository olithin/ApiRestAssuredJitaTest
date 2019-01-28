package RestAsserdSample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ValidateResponse {


@Test
    public static void verify() {

        RestAssured.baseURI = "https://maps.googleapis.com";
        given()
                .param("location", "-33.875154").param("radius ","500")
                .param("types", "restaurant").param("query","restaurants+in+Sydney")
                .param("key","AIzaSyCQ0J0M7zuqhv3cxJ4HF6HFjaNA1IvqSJc").

        when().
                get("/maps/api/place/textsearch/json").
        then().assertThat().statusCode(200).and().
        contentType(ContentType.JSON).and()
                .body("results[0].name",equalTo("Tetsuya's Restaurant"));
               // .body("results[0].geometry.viewport.northeast.lat",equalTo("-33.85286117010728"));
        System.out.println("Request Execurted success");
    }
}
