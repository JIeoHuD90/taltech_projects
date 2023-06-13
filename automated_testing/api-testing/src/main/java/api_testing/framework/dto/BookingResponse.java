package api_testing.framework.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect
public class BookingResponse {
    private Integer bookingid;
    private Booking booking;
}
