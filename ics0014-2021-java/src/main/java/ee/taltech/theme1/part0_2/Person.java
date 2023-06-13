package ee.taltech.theme1.part0_2;

/**
 * referenced in https://olegpahhomov.gitlab.io/first-time-java/classes/
 */
public class Person {

    public static void main(String[] args) {
        Person bob = new Person();
        bob.setName("Bob");
        System.out.println(bob.getName());

        Person mary = new Person("Mary");
        System.out.println(mary.getName());
    }

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
