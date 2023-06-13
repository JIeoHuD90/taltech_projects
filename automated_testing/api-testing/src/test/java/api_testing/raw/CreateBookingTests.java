package api_testing.raw;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTests {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking";

    private static final String BOOKING_REQUEST_BODY = "{\n" +
            "\"firstname\": " + "\"German\",\n" +
            "\"lastname\": " + "\"Mumma\",\n" +
            "\"totalprice\": " + "100\n," +
            "\"depositpaid\": " + "true\n," +
            "\"bookingdates\": {\n" +
            "\"checkin\": " + "\"2023-03-23\",\n" +
            "\"checkout\": " + "\"2023-03-25\"\n" +
            "}\n" +
            "}";

    @Test
    public void givenBookingRequestBody_whenCreateBooking_thenShouldHaveBookingId() {
        given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(BOOKING_REQUEST_BODY)
                .when()
                .post(API_URL)
                .then()
                .body("bookingid", notNullValue());
    }

    @Test
    public void givenBookingRequestBody_whenCreateBooking_thenShouldHaveFirstnameAndLastName() {
        given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(BOOKING_REQUEST_BODY)
                .when()
                .post(API_URL)
                .then()
                .body("booking.firstname", equalTo("German"))
                .body("booking.lastname", equalTo("Mumma"));
    }

}
