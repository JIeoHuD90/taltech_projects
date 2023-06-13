package ee.taltech.theme2.part3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UniqueElementsTest {

    @Test
    void nothing_happens_to_null() {
        assertNull(UniqueElements.removeDuplicates(null));
    }

    @Test
    void nothing_happens_to_empty_list() {
        assertEquals(new ArrayList<>(), UniqueElements.removeDuplicates(new ArrayList<>()));
    }

    @Test
    void nothing_happens_to_list_of_unique_elements() {
        List<Integer> initialList = List.of(1, 2, 3);
        List<Integer> duplicateFreeList = UniqueElements.removeDuplicates(initialList);
        assertEquals(initialList, duplicateFreeList);
    }

    @Test
    void a_list_of_non_unique_elements_has_duplicates_removed() {
        List<Integer> initialList = List.of(1, 1, 1, 2);
        List<Integer> duplicateFreeList = UniqueElements.removeDuplicates(initialList);
        assertEquals(List.of(1, 2), duplicateFreeList);
    }
}
