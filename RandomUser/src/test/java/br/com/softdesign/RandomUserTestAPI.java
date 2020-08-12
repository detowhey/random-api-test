package br.com.softdesign;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RandomUserTestAPI {

    @BeforeClass
    public static void configurarValorPadrao() {
        RestAssured.baseURI = "https://randomuser.me/api/";
    }

    @Test
    public void pesquisarListaDeUsuarios() {
        given().log().params().contentType(ContentType.JSON).param("results", 20)
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .body("info.results", is(20))
                .body("results", hasSize(20));
    }

    @Test
    public void pesquisarUsuarioBrasileiro() {
        given().log().params().contentType(ContentType.JSON).param("nat", "br")
                .when()
                .get()
                .then().log().body()
                .statusCode(200)
                .body("results[0].nat", equalToIgnoringCase("br"));
    }

    @Test
    public void pesquisarUmUsuarioDaPagina() {
        given().log().params().contentType(ContentType.JSON).param("page", 3)
                .when()
                .get()
                .then().log().body()
                .rootPath("info")
                .statusCode(200)
                .body("page", equalTo(3))
                .body("page", isA(Integer.class))
                .body("results", equalTo(1));
    }

    @Test
    public void pesquisarDoUsuarioEmailNome() {
        Response resposta = given().log().params().contentType(ContentType.JSON)
                .param("inc", "name,email").when().get().then().log().body().extract().response();

        Object obj = resposta.jsonPath().get("results[0]");
        System.out.println(resposta.jsonPath().get("results[0]").getClass());
        System.out.println(obj.toString());

    }
}
