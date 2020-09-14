package br.com.henriquealmeida;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RandomUserTestAPI {

    @BeforeClass
    public static void addStandardParameter() {
        RestAssured.baseURI = "https://randomuser.me/";
        RestAssured.basePath = "api/";
    }

    @Test
    public void searchUserList() {

        given().log().params().contentType(ContentType.JSON).param("results", 20)
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("info.results", is(20))
                .body("results", hasSize(20));
    }

    @Test
    public void searchBrazilianUser() {

        given().log().params().contentType(ContentType.JSON).param("nat", "br")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0].nat", equalToIgnoringCase("br"));
    }

    @Test
    public void searchUserByNationality() {

        given().log().params().contentType(ContentType.JSON).param("nat", "br,us,es,ca")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0].nat", anyOf(equalToIgnoringCase("br"), equalToIgnoringCase("us"),
                        equalToIgnoringCase("es"), equalToIgnoringCase("ca")));
    }

    @Test
    public void searchUserPage() {

        given().log().params()
                .contentType(ContentType.JSON).param("page", 3)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .rootPath("info")
                .body("page", equalTo(3))
                .body("page", isA(Integer.class))
                .body("results", equalTo(1));
    }

    @Test
    public void searchUserEmail() {

        given().log().params().contentType(ContentType.JSON).param("inc", "name,email")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0]", not(hasKey("gender")))
                .body("results[0]", hasKey("name"))
                .body("results[0]", hasKey("email"));
    }
}
