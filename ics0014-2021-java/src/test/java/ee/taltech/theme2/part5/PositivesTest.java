package ee.taltech.theme2.part5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositivesTest {

    @Test
    void null_throws_illegal_argument() {
        assertThrows(IllegalArgumentException.class, () -> Positives.removeNegatives(null));
    }

    @Test
    void removeNegatives_removes_only_the_negative_integers() {
        assertEquals(List.of(), Positives.removeNegatives(List.of(-2, -4, -1, 1)));
    }

    @Test
    void removeNegative_does_not_remove_positives() {
        assertEquals(List.of(2, 4, 1), Positives.removeNegatives(List.of(2, 4, 1)));
    }

    @Test
    void removeNegatives_removes_all_negative_integers() {
        assertEquals(List.of(1), Positives.removeNegatives(List.of(-2, -4, -1, -1)));
    }
}
