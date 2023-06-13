package api_testing.framework;

import api_testing.framework.controller.BookingApi;
import api_testing.framework.dto.Booking;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;

public class SmokeTests {
    private static BookingApi bookingApi;

    @BeforeAll
    public static void initialize() {
        bookingApi = new BookingApi();
    }

    @Test
    public void whenGetBookingIdsIsCalled_thenReturnHttp200() {
        bookingApi.getBookingIds()
                .then()
                .statusCode(200);
    }

    @Test
    public void whenGetBookingByIdIsCalled_thenReturnHttp200() {
        Integer bookingId = 7;

        bookingApi.getBookingById(bookingId)
                .then()
                .statusCode(200);
    }

    @Test
    public void givenBookingRequest_whenCreateBookingIsCalled_thenReturnHttp200() {
        Booking booking = Booking.getFullPayload();

        bookingApi.createBooking(booking,JSON.toString())
                .then()
                .statusCode(200);
    }
}
