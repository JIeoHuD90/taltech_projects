package ee.taltech.theme1.part1;

//these are imports, if you want to use some other class, it needs to be "imported".
// exception to this rule is one package of Java code (java.lang)
// the package where Strings and numbers live..

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloClassTest {

    //todo 1.7 run the test and fix the test
    @Test
    void helloWorld_author_is_donald_duck() {
        Assertions.assertEquals("Donald Duck", HelloClass.author());
    }

    //todo 1.8 run the test and fix the test
    @Test
    void helloWorld_hobby_is_football() {
        String hobby = new HelloClass().hobby();
        //notice that here is no Assert.assertEquals. This is called "static import". Look into imports
        assertEquals("Football", hobby);
    }

    //todo 1.9 create a new flow and a test to go with it
    // there could be a setter in HelloWorld to location (setter is a method whose whole purpose is to set new value to the field)
    // logic could be... you create HelloWorld, it's location is Greenland
    // then you call your method and then it's location is something new


    //todo 1.10 already here? Congratz! Help your neighbours!
}
