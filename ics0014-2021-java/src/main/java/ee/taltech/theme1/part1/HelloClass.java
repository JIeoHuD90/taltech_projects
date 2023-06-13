package ee.taltech.theme1.part1;

//HelloClass is a class
public class HelloClass {

    //these are fields
    //static means it's a field of a class
    private static String greeting = "Howdy neighbour!";
    //having no static means it's a field of an instance of a class, instance field in short
    private String location = "Greenland";

    //this is a special main function. It special because it runs the class (or application).
    public static void main(String[] args) {
        //todo 1.1 run main and get it to write your name
        //IntelliJ protip write sout and then tab

        //todo 1.2 find a way to print the value of the field "greeting"


        //todo 1.3 find a way to call method "nothing"


        //todo 1.4 find a way to print the value of the field "location".


        //todo 1.5 find a way to change "location" to something warmer, like Abu Dhabi
        // try not to change location on line 10, instead set there new value or something


        //todo 1.6 find a way to call method "something"


        //todo 1.7 while main method is fun, developers write tests instead
        // go to HelloClassTest and continue there
    }

    //static method, void doesn't return anything
    public static void nothing(){
        System.out.println("nothing");
    }

    //instance method
    public void something(){
        System.out.println("nothing");
    }

    //static method, this one returns a String. Notice String and return statement
    public static String author(){
        return "Donald";
    }

    //instance method, this one returns a String.
    public String hobby(){
        return "some ball game";
    }
}
