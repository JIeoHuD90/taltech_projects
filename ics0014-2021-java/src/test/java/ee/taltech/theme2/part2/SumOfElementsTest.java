package ee.taltech.theme2.part2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SumOfElementsTest {

    @Test
    void sum_is_a_sum_of_numbers() {
        Integer biggest = SumOfElements.sum(List.of(1, 3, 2));
        assertNotNull(biggest);
        assertEquals(6, (int) biggest);
    }

    @Test
    void on_null() {
        //todo 12.3 this is your decision what to return on "null" input
        // is sum of nothing nothing or a zero?
        // in the next lecture we will talk about Java Optional<T>
    }

    @Test
    void on_no_elements() {
        //todo 12.4 this is your decision what to return on "empty list" input
    }

}
