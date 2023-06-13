package ee.taltech.theme1.part0_4;

public class VisibilityGuide {

    //access only inside the class
    private String privateField;
    //access only inside the package
    String defaultField; //won't be using in ICS0014, usually used for testing private methods (which are updated to default)
    //access only inside the package for subclasses
    protected String protectedField; //won't be using in ICS0014, used together with inheritance
    //access from everywhere
    public String publicField;

}
