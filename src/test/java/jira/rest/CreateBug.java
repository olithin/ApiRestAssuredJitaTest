package jira.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateBug {
    @Test
    public void verifyResponse() throws IOException {

        System.out.println(System.getProperty("user.dir"));

        String requestBody = generateString("jsonLoginJira");

        RestAssured.baseURI = "http://localhost:8080/";
        Response res = given().
                contentType(ContentType.JSON).

                body(requestBody).
                when().
                post("/rest/auth/1/session").
                then().assertThat().statusCode(200).

                extract().response();

        String respose = res.asString();
        System.out.println(respose);

        JsonPath jsonPath = new JsonPath(respose);
        String ssesionId = jsonPath.getString("session.value");


        String createBugBody = generateString("createbug.json");
given().
                contentType(ContentType.JSON).
                cookie("JSESSIONID=753E26DCE5B96605A411684935950C0A").

                body(createBugBody).
                when().
                post("rest/api/2/issue").
                then().assertThat().statusCode(201).log().all();

    }

    public static String generateString(String filename) throws IOException{
        String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+filename;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

}
