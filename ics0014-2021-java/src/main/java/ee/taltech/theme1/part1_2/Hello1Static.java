package ee.taltech.theme1.part1_2;

//HelloStatic is a class
public class Hello1Static {

    //these are fields
    //static means it's a field of a class
    private static String greeting = "Howdy neighbour!";
    //having no static means it's a field of an instance of a class, instance field in short
    private String location = "Greenland";

    //this is a special main function. It special because it runs the class (or application).
    public static void main(String[] args) {
        //todo 1.2 find a way to print the value of the field "greeting"

        //todo 1.3 find a way to call method "nothing"
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
