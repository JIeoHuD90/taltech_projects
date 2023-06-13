package ee.taltech.theme1.part0_6;

import java.time.LocalDate;
import java.time.LocalDateTime;

//example of class with static methods only
//code copied from real project
public class DateUtil {

    public static LocalDateTime getLatest(LocalDateTime date1, LocalDateTime date2) {
        if (date1 == null) return date2;
        if (date2 == null) return date1;
        return date1.isAfter(date2) ? date1 : date2;
    }

    public static LocalDate getLatest(LocalDate date1, LocalDate date2) {
        if (date1 == null) return date2;
        if (date2 == null) return date1;
        return date1.isAfter(date2) ? date1 : date2;
    }
}
