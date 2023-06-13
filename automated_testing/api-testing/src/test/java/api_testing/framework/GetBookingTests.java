package api_testing.framework;

import api_testing.framework.controller.BookingApi;
import api_testing.framework.dto.Booking;
import api_testing.framework.dto.BookingResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class GetBookingTests {
    private static BookingApi bookingApi;

    @BeforeAll
    public static void initialize() {
        bookingApi = new BookingApi();
    }

    @Test
    public void whenGetBookingIdsIsCalled_thenShouldHaveMoreThanOne() {
        bookingApi.getBookingIds()
                .then()
                .body("$", hasSize(greaterThan(1)));
    }

    @Test
    public void givenSpecificBookingId_whenGetBookingByIdIsCalled_thenShouldContainAdditionalNeeds() {
        Booking expectedBooking = Booking.getFullPayload();

        BookingResponse bookingResponse = bookingApi
                .createBooking(expectedBooking,JSON.toString())
                .as(BookingResponse.class);

        Booking actualBooking = bookingApi
                .getBookingById(bookingResponse.getBookingid())
                .as(Booking.class);

        assertThat(actualBooking.getAdditionalneeds()).isEqualTo(expectedBooking.getAdditionalneeds());
    }

}
