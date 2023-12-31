package api_testing.raw;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class SmokeTests {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";

    @Test
    public void whenGetBookingIdsIsCalled_thenReturnHttp200() {
        given()
                .when()
                .get(API_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void whenGetBookingByIdIsCalled_thenReturnHttp200() {
        String payload = "{\n" +
                "\"firstname\": " + "\"German\",\n" +
                "\"lastname\": " + "\"Mumma\",\n" +
                "\"totalprice\": " + "100\n," +
                "\"depositpaid\": " + "true\n," +
                "\"bookingdates\": {\n" +
                "\"checkin\": " + "\"2023-03-23\",\n" +
                "\"checkout\": " + "\"2023-03-25\"\n" +
                "}\n" +
                "}";

        int bookingId = given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .extract()
                .path("bookingid");
        given()
                .when()
                .get(API_URL + "/" + bookingId)
                .then()
                .statusCode(200);
    }

    @Test
    public void givenBookingRequest_whenCreateBookingIsCalled_thenReturnHttp200() {
        String payload = "{\n" +
                "\"firstname\": " + "\"German\",\n" +
                "\"lastname\": " + "\"Mumma\",\n" +
                "\"totalprice\": " + "100\n," +
                "\"depositpaid\": " + "true\n," +
                "\"bookingdates\": {\n" +
                "\"checkin\": " + "\"2023-03-23\",\n" +
                "\"checkout\": " + "\"2023-03-25\"\n" +
                "}\n" +
                "}";

        given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .statusCode(200);
    }
}
