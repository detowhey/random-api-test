package br.com.softdesign;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RandomUserTestAPI {

    @BeforeClass
    public static void configurarValorPadrao() {
        RestAssured.baseURI = "https://randomuser.me/";
        RestAssured.basePath = "api/";
    }

    @Test
    public void pesquisarListaDeUsuarios() {

        given().log().params().contentType(ContentType.JSON).param("results", 20)
                .when().get()
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("info.results", is(20))
                .body("results", hasSize(20));
    }

    @Test
    public void pesquisarUsuarioBrasileiro() {

        given().log().params().contentType(ContentType.JSON).param("nat", "br")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0].nat", equalToIgnoringCase("br"));
    }

    @Test
    public void pesquisarUsuarioPorNacionalidade() {

        given().log().params().contentType(ContentType.JSON).param("nat", "br,us,es,ca")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0].nat", anyOf(equalToIgnoringCase("br"), equalToIgnoringCase("us"),
                        equalToIgnoringCase("es"), equalToIgnoringCase("ca")));
    }

    @Test
    public void pesquisarUmUsuarioDaPagina() {

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
    @Ignore
    public void pesquisarDoUsuarioEmailNome() {

        Response resposta = given().log().params().contentType(ContentType.JSON).param("inc", "name,email")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK).extract().response();

        Map<String, Object> objetoMap = resposta.jsonPath().getMap("results[0]");

        assertFalse(objetoMap.containsKey("gender"));
        assertTrue(objetoMap.containsKey("name"));
        assertTrue(objetoMap.containsKey("email"));
    }

    @Test
    public void pesquisarDoUsuarioEmailSemMap() {

        given().log().params().contentType(ContentType.JSON).param("inc", "name,email")
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0]", not(hasKey("gender")))
                .body("results[0]", hasKey("name"))
                .body("results[0]", hasKey("email"));
    }
}
