package br.com.henriquealmeida.functionalTest;

import br.com.henriquealmeida.service.BaseService;
import br.com.henriquealmeida.utilities.JSONUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookingTestAPI{

    @BeforeClass
    public static void addStandardParameter() {
        RestAssured.baseURI = BaseService.BOOKING_URI_SERVICE;
        RestAssured.basePath = BaseService.BOOKING_URI_PATH;
    }

    private final String BOOKING_JSON = JSONUtil.bookingJSONBuilder("Henrique", "Almeida",
            80.05, false,"2020-07-07","2020-10-10", "Lunch");

    @Test
    public void listBookings() {
        Map<String, Integer> mapObject = given().when().get().jsonPath().getMap("[0]");

        assertFalse(mapObject.isEmpty());
        assertTrue(mapObject.containsKey("bookingid"));
    }

    @Test
    public void createBooking() {

        given().contentType(ContentType.JSON).body(BOOKING_JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("bookingid"))
                .body("bookingid", notNullValue())
                .body("bookingid", isA(Integer.class))
                .body("booking.firstname", equalToIgnoringCase("henrique"));
    }

    @Test
    public void validateCreatedBooking() {

        int idCreated = given().contentType(ContentType.JSON).body(BOOKING_JSON)
                .when()
                .post()
                .jsonPath().getInt("bookingid");

        given().contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("find{it.bookingid == " + idCreated + "}", notNullValue())
                .body("find{it.bookingid == " + idCreated + "}.bookingid", equalTo(idCreated));
    }

    @Test
    public void deleteBooking() {

        int idValid = given().when().get().jsonPath().getInt("bookingid[0]");

        given().contentType(ContentType.JSON).auth().preemptive().basic("admin", "password123")
                .when()
                .delete(Integer.toString(idValid))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body(containsString("Created"));
    }
}
