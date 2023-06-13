package ee.taltech.theme2.part4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SorterTest {

    @Test
    void sortAsc_sorts_numbers_in_asc_order() {
        assertEquals(List.of(1, 2, 3, 4), Sorter.sortAsc(List.of(1,3,4,2)));
    }

    @Test
    void sortDesc_sorts_numbers_in_desc_order() {
        assertEquals(List.of(4, 3, 2, 1), Sorter.sortDesc(List.of(1,3,4,2)));
    }
}
