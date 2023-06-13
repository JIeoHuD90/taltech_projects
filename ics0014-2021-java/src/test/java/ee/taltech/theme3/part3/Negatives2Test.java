package ee.taltech.theme3.part3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class Negatives2Test {

    @Test
    void on_null_nothing_is_returned() {
        assertNull(Negatives2.biggestNegative(null));
    }

    @Test
    void on_no_elements_nothing_is_returned() {
        assertNull(Negatives2.biggestNegative(new ArrayList<>()));
    }

    @Test
    void biggest_negative_is_not_inside_a_positive_list() {
        assertNull(Negatives2.biggestNegative(List.of(1, 3, 2, 0)));
    }

    @Test
    void biggest_element_returns_biggest_negative_element_of_the_list() {
        Integer biggestNegative = Negatives2.biggestNegative(List.of(-3, -200, 0));
        assertNotNull(biggestNegative);
        assertEquals(-1, (int) biggestNegative);
    }

    //todo 103.3 sumOfNegatives I think we should create null, empty list, negatives list, positives list, mixed list
}
