package ee.taltech.theme3.part2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Positives2Test {

    //todo 102.2 notice how we are reusing the same tests :D
    @Test
    void null_throws_illegal_argument() {
        assertThrows(IllegalArgumentException.class, () -> Positives2.removeNegatives(null));
    }

    @Test
    void removeNegatives_removes_only_the_negative_integers() {
        assertEquals(List.of(), Positives2.removeNegatives(List.of(-2, -4, -1, 1)));
    }

    @Test
    void removeNegative_does_not_remove_positives() {
        assertEquals(List.of(2, 4, 1), Positives2.removeNegatives(List.of(2, 4, 1)));
    }

    @Test
    void removeNegatives_removes_all_negative_integers() {
        assertEquals(List.of(1), Positives2.removeNegatives(List.of(-2, -4, -1, -1)));
    }

    //todo 102.4 add some tests... null, negatives, positives, mixed
    @Test
    void removeNegativesSortAsc() {
    }

    @Test
    void getFirstPositive() {
    }

    @Test
    void first_positive_expects_a_valid_input() {
        assertThrows(IllegalArgumentException.class, () -> Positives2.firstPositive(null));
    }

    @Test
    void first_positive_is_empty_when_no_input() {
        assertEquals(Optional.empty(), Positives2.firstPositive(List.of()));
    }

    @Test
    void first_positive_is_empty_when_negative_input() {
        assertEquals(Optional.empty(), Positives2.firstPositive(List.of(-1, -2, -3)));
    }

    @Test
    void first_positive_is_first_positive() {
        assertEquals(Optional.of(4), Positives2.firstPositive(List.of(-1, -2, -3, 4)));
    }
}
