package ee.taltech.theme1.part0_3;

/**
 * Before you delve into this read https://olegpahhomov.gitlab.io/first-time-java/static-vs-instance/
 */
//ClassGuide is a class
// There can be multiple classes in one file (it is recommended to use just one)
// Only one class must be public. Public class name must match filename
public class ClassGuide {
    //notice the order: 1. fields, 2. constructor(s), 3.methods

    //In the real world static methods are used for utility classes
    // for example NumberUtils what has different math functions: summing, subtracting, comparing
    // while instance methods are used for stateful classes
    // for example Fridge has different amounts of food in it (let's say 0 to 10 with 0 being empty and 10 being full)

    //these are fields
    //static means it's a field of a class
    private static String staticField = "Static field";
    //no static means it's a field of an instance of a class, instance field in short
    private String instanceField = "Instance field";

    //this is a constructor, this is "default" constructor because it has no inputs
    // default constructor doesn't need to be defined if it's the only one
    // you see it omitted in most of the examples
    public ClassGuide() {
        //however you can have some logic here like setting default values for your instance fields
    }

    //this is a special method that you can run on a class
    public static void main(String[] args) {
        //static fields are called using the reference of a class
        String staticField1 = ClassGuide.staticField;
        String staticField2 = staticField; //works because we are in the same class
        System.out.println(staticField1);
        System.out.println(staticField2);

        //static methods (defined lower) are called using the reference of a class
        ClassGuide.staticVoidMethod();
        staticVoidMethod(); //works because we are in the same class
        String staticMethodReturnValue1 = ClassGuide.staticMethod();
        String staticMethodReturnValue2 = staticMethod(); //works because we are in the same class
        System.out.println(staticMethodReturnValue1);
        System.out.println(staticMethodReturnValue2);

        //instance fields are called using the instance of a class
        ClassGuide classGuide = new ClassGuide(); // this is an instance creation using a constructor (Line16)
        String instanceField = classGuide.instanceField;
        System.out.println(instanceField);

        //instance methods (defined lower) are called using an instance of a class
        classGuide.instanceVoidMethod();
        String instanceReturnValue = classGuide.instanceMethod();
        System.out.println(instanceReturnValue);

        //you can call class methods on an instance of a class as well, but that is considered bad practise
        String staticField3 = classGuide.staticField;
    }

    //these are some methods
    //static means it's a class method, void doesn't return anything
    public static void staticVoidMethod(){
        System.out.println("inside a void STATIC method");
    }

    //no static means it's a instance method, void doesn't return anything
    public void instanceVoidMethod(){
        System.out.println("inside a void INSTANCE method");
    }

    //static method, this one returns a String. Notice String and return statement
    public static String staticMethod(){
        return "return a String from STATIC method";
    }

    //instance method, this one returns a String.
    public String instanceMethod(){
        return "return a String from INSTANCE method";
    }
}
