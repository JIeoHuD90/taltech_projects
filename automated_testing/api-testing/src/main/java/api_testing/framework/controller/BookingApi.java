package api_testing.framework.controller;

import api_testing.framework.dto.Booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingApi {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String BOOKING_API = BASE_URL + "/booking/";


    public BookingApi(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    public Response getBookingIds(){
        return given()
                .accept(JSON.toString())
                .get(BOOKING_API);
    }


    public Response getBookingById(Integer bookingId) {
        return given()
                .accept(JSON.toString())
                .get(BOOKING_API + bookingId);
    }

    public static Response createBooking(Booking booking, String contentType) {
        return given()
                .contentType(JSON.toString())
                .accept(contentType)
                .body(booking)
                .post(BOOKING_API);
    }

    public static Response putBooking(Booking bookingPayload, String token, int bookingId) {
        return given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(bookingPayload)
                .cookie("token", token)
                .when()
                .put(BOOKING_API + bookingId);
    }


    public static Response deleteBooking(String token, int bookingId) {
        return given()
                .contentType(JSON.toString())
                .cookie("token", token)
                .when()
                .delete(BOOKING_API + bookingId);
    }
}

