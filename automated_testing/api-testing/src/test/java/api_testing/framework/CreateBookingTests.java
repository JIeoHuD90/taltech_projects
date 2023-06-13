package api_testing.framework;

import api_testing.framework.controller.BookingApi;
import api_testing.framework.dto.Booking;
import api_testing.framework.dto.BookingResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingTests {

    private static BookingApi bookingApi;

    private Booking expectedBooking;

    @BeforeAll
    public static void initialize() {
        bookingApi = new BookingApi();
    }

    @BeforeEach
    public void initializeBooking() {
        expectedBooking = Booking.getFullPayload();
    }

    @Test
    public void givenBookingRequestBody_whenCreateBooking_thenShouldHaveBookingId() {
        BookingResponse bookingResponse = bookingApi
                .createBooking(expectedBooking, JSON.toString())
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotNull();
    }

    @Test
    public void givenBookingRequestBody_whenCreateBooking_thenShouldHaveFirstnameAndLastName() {
        Response response = bookingApi
                .createBooking(expectedBooking, JSON.toString());
        assertThat(response.statusCode()).isEqualTo(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);

        assertThat(bookingResponse.getBooking())
                .extracting("firstname", "lastname")
                .contains(expectedBooking.getFirstname(), expectedBooking.getLastname());
    }


    @Test
    public void givenBookingFromFile_whenCreateBooking_thenShouldHaveProblematicAdditionalNeeds() throws IOException {
        Booking expectedBooking = Booking.buildFromResource("jira123.json");

        Booking actualBooking = bookingApi
                .createBooking(expectedBooking,JSON.toString())
                .as(BookingResponse.class)
                .getBooking();

        assertThat(actualBooking.getAdditionalneeds()).isEqualTo(expectedBooking.getAdditionalneeds());
    }
    @Test
    public void postBookingWithWrongAcceptHeaderShouldReturn418() {
        ValidatableResponse bookingResponse = bookingApi
                .createBooking(expectedBooking, "asds")
                .then()
                .statusCode(418);

    }



}
