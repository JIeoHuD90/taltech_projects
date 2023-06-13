package ee.taltech.theme3.part4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonGrouper2 {

    //todo 104.1
    // this draws heavily from lesson2.part10
    // fix tests and add logic for these two methods, however use streams now
    public static Map<String, Person2> mapPeopleByIdCode(List<Person2> people) {
        return new HashMap<>();
    }

    public static Map<String, Person2> mapPeopleByFirstAndLastName(List<Person2> people) {
        return new HashMap<>();
    }

    //todo 104.2 can you make this work as well?
    // ... is a varargs argument
    public static Map<String, Person2> mapPeopleByIdCode(Person2... people) {
        return new HashMap<>();
    }

    //todo 104.3 while lesson2.part10 was hardcore, this one should be quite easy
    // you need to group on the stream
    public static Map<String, List<Person2>> groupPeopleByFirstAndLastNameMultiple(List<Person2> people) {
        return new HashMap<>();
    }
}
