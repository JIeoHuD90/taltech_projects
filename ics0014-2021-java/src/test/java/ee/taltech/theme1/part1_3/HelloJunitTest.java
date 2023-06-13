package ee.taltech.theme1.part1_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloJunitTest {

    //todo 1.7 run the test and fix the test
    @Test
    void helloWorld_author_is_donald_duck() {
        Assertions.assertEquals("Donald Duck", HelloJunit.author());
    }

    //todo 1.8 run the test and fix the test
    @Test
    void helloWorld_hobby_is_football() {
        String hobby = new HelloJunit().hobby();
        //notice that here is no Assert.assertEquals. This is called "static import". Look into imports
        assertEquals("Football", hobby);
    }

    //todo 1.9 create a new flow and a test to go with it
    // there could be a setter in HelloJunit to location (setter is a method whose whole purpose is to set new value to the field)
    // 4 steps:
    // 1) you create HelloWorld
    // 2) assert that location is Greenland
    // 3) call a method to set new location
    // 4) assert that location has changed


    //todo 1.10 already here? Congratz! Help your neighbours!
}
