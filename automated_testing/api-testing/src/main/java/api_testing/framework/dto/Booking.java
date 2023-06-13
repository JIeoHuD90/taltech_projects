package api_testing.framework.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.IOException;
import java.io.InputStream;

import static java.util.concurrent.TimeUnit.DAYS;

@Data
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    private BookingDates bookingdates;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String additionalneeds;

    public static Booking getFullPayload() {
        Faker faker = new Faker();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(faker.date().future(1, DAYS));
        bookingDates.setCheckout(faker.date().future(5, DAYS));

        return Booking.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().randomDigit())
                .depositpaid(false)
                .bookingdates(bookingDates)
                .additionalneeds(faker.howIMetYourMother().catchPhrase())
                .build();
    }

    public static Booking buildFromResource(String fileName) throws IOException {
        String filePath = "test_data/" + fileName;
        ClassLoader classLoader = Booking.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, Booking.class);

    }
}
