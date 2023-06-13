package ee.taltech.theme3.part4;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonGrouper2Test {

    static List<Person2> testPeople = List.of(
            new Person2("11111", "Great", "President"),
            new Person2("22222", "Donald", "Duck"),
            new Person2("33333", "Cristiano", "Ronaldo"));

    @Test
    void grouping_people_by_id_code_divides_them_to_id_code_and_person_pairs() {
        Map<String, Person2> map = PersonGrouper2.mapPeopleByIdCode(testPeople);
        assertEquals(3, map.size());
        assertNull(map.get("-1"));
        Person2 person1 = map.get("11111");
        assertEquals("Great", person1.getFirstName());
        Person2 person2 = map.get("22222");
        assertEquals("Donald", person2.getFirstName());
        Person2 person3 = map.get("33333");
        assertEquals("Cristiano", person3.getFirstName());
    }

    @Test
    void grouping_people_by_first_and_last_name_code_divides_them_to_full_name_and_person_pairs() {
        Map<String, Person2> map = PersonGrouper2.mapPeopleByFirstAndLastName(testPeople);
        assertEquals(3, map.size());
        assertNull(map.get("-1"));
        Person2 person1 = map.get("Donald Duck");
        assertEquals("Donald", person1.getFirstName());
        Person2 person2 = map.get("Great President");
        assertEquals("Great", person2.getFirstName());
        Person2 person3 = map.get("Cristiano Ronaldo");
        assertEquals("Cristiano", person3.getFirstName());
    }
}
