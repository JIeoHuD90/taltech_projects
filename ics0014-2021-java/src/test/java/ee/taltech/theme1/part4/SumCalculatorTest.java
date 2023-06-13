package ee.taltech.theme1.part4;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//you can run all tests from here
class SumCalculatorTest {

    @Test
    void sum_is_a_sum_of_2_numbers() {
        assertEquals(0, SumCalculator.sum(0, 0));
        assertEquals(5, SumCalculator.sum(5, 0));
        assertEquals(5, SumCalculator.sum(0, 5));
        assertEquals(10, SumCalculator.sum(5, 5));

        assertEquals(-4, SumCalculator.sum(-4, 0));
        assertEquals(-5, SumCalculator.sum(0, -5));
        assertEquals(-9, SumCalculator.sum(-4, -5));
    }

    //a single test can be run from here
    @Test
    void sumAbsolute_is_a_sum_of_2_abs_numbers() {
        assertEquals(0, SumCalculator.sumAbsolute(0, 0));
        assertEquals(5, SumCalculator.sumAbsolute(5, 0));
        assertEquals(5, SumCalculator.sumAbsolute(0, 5));
        assertEquals(10, SumCalculator.sumAbsolute(5, 5));

        assertEquals(4, SumCalculator.sumAbsolute(-4, 0));
        assertEquals(5, SumCalculator.sumAbsolute(0, -5));
        assertEquals(9, SumCalculator.sumAbsolute(-4, -5));
    }

    @Test
    void sumObject_is_a_sum_of_2_number() {
        assertEquals(0, SumCalculator.sumObjects(0, 0));
        assertEquals(5, SumCalculator.sumObjects(5, 0));
        assertEquals(5, SumCalculator.sumObjects(0, 5));
        assertEquals(10, SumCalculator.sumObjects(5, 5));

        assertEquals(-4, SumCalculator.sumObjects(-4, 0));
        assertEquals(-5, SumCalculator.sumObjects(0, -5));
        assertEquals(-9, SumCalculator.sumObjects(-4, -5));
    }

    //todo 4.2 this is tricky, but I am sure you can do it :)
    @Test
    void sumObject_is_null_safe() {
        assertEquals(3, SumCalculator.sumObjects(null, 3));
        assertEquals(3, SumCalculator.sumObjects(3, null));
        assertNull(SumCalculator.sumObjects(null, null));
    }
}
