package ee.taltech.theme1.part1_2;

//HelloStatic is a class
public class Hello2Instance {

    //these are fields
    //static means it's a field of a class
    private static String greeting = "Howdy neighbour!";
    //having no static means it's a field of an instance of a class, instance field in short
    private String location = "Greenland";

    //this is a special main function. It special because it runs the class (or application).
    public static void main(String[] args) {
        //todo 1.4 find a way to print the value of the field "location".
        // compared to greeting this is an instance field, so you need to create a new instance
        // Hello2Instance hello2Instance = new Hello2Instance()


        //todo 1.5 find a way to change "location" to something warmer, like Abu Dhabi
        // try not to change location on line 10, instead find a way to assign a new value

        //todo 1.6 find a way to call method "something"
    }

    //static method, void doesn't return anything
    public static void nothing(){
        System.out.println("nothing");
    }

    //instance method
    public void something(){
        System.out.println("nothing");
    }
}
