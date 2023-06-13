package ee.taltech.theme2.part10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonGrouper {

    //todo 20.1 fix tests and add logic for these two methods
    public static Map<String, Person> mapPeopleByIdCode(List<Person> people) {
        return new HashMap<>();
    }

    public static Map<String, Person> mapPeopleByFirstAndLastName(List<Person> people) {
        return new HashMap<>();
    }

    //todo 20.2 can you make this work as well?
    // ... is a varargs argument
    public static Map<String, Person> mapPeopleByIdCode(Person... people) {
        return new HashMap<>();
    }

    //todo 20.3 this is quite hardcore, but what if you have people with the same name in your list of people
    // then you will have to return name and a list of people, create a test for it as well
    // try to do it without Streams, in the next lesson we will learn about the streams
    public static Map<String, List<Person>> groupPeopleByFirstAndLastNameMultiple(List<Person> people) {
        return new HashMap<>();
    }
}
