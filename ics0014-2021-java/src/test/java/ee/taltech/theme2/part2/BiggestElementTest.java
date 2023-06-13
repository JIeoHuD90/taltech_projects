package ee.taltech.theme2.part2;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BiggestElementTest {

    @Test
    void on_null_nothing_is_returned() {
        assertNull(BiggestElement.findBiggest(null));
    }

    @Test
    void on_no_elements_nothing_is_returned() {
        assertNull(BiggestElement.findBiggest(new ArrayList<>()));
    }

    @Test
    void biggest_element_returns_biggest_element_of_the_list() {
        Integer biggest = BiggestElement.findBiggest(List.of(1, 3, 2));
        assertNotNull(biggest);
        assertEquals(3, (int) biggest);
    }

}
