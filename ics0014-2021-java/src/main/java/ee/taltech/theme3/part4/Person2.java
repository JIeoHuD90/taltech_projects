package ee.taltech.theme3.part4;

public class Person2 {

    private String idCode;
    private String firstName;
    private String lastName;

    public Person2(String idCode, String firstName, String lastName) {
        this.idCode = idCode;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
