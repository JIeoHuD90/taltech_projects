package api_testing.framework;

import api_testing.framework.authentication.AuthenticationApi;
import api_testing.framework.controller.BookingApi;
import api_testing.framework.dto.Booking;
import api_testing.framework.dto.BookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;


public class PutDeleteBookingTests {

    @Test
    public void putBookingShouldReturnHttp200() {
        Booking bookingPayload = Booking.getFullPayload();
        String token = AuthenticationApi.fetchToken();

        BookingResponse bookingResponse = BookingApi
                .createBooking(bookingPayload,JSON.toString())
                .as(BookingResponse.class);

        BookingApi
                .putBooking(bookingPayload, token, bookingResponse.getBookingid())
                .then()
                .statusCode(200);
    }


    @Test
    public void deleteBookingShouldReturnHttp201() {
        Booking bookingPayload = Booking.getFullPayload();
        String token = AuthenticationApi.fetchToken();

        BookingResponse bookingResponse = BookingApi
                .createBooking(bookingPayload,JSON.toString())
                .as(BookingResponse.class);

        BookingApi
                .deleteBooking(token, bookingResponse.getBookingid())
                .then()
                .statusCode(201);
    }
}
