package ee.taltech.theme1.part0_5;

import static java.lang.String.format;
import static java.lang.System.out;

//each java object extends java.lang.Object (you don't have to write it)
//there are 3 useful methods which we can override
//toString - return a better representation of an object
//equals and hashcode (overriden together)
public class ObjectGuide extends Object {

    public String name;

    public ObjectGuide(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        out.println("--------==TO STRING==--------");
        out.println(new ObjectGuide("hola"));
        out.println(new OverridenObjectGuide("hola"));
        out.println("-----------------------------");

        //this is a quick example of String.format
        out.println(String.format("Hello %s", "World"));

        out.println("--------==EQUALITY==--------");
        out.println(format("ObjectGuide == %s", new ObjectGuide("hola") == new ObjectGuide("hola"))); //intelliJ highlights == , protip: read what intelliJ says
        out.println(format("ObjectGuide equals %s", new ObjectGuide("hola").equals(new ObjectGuide("hola"))));
        out.println(format("OverridenObjectGuide == %s", new OverridenObjectGuide("hola") == new OverridenObjectGuide("hola")));
        out.println(format("OverridenObjectGuide equals %s", new OverridenObjectGuide("hola").equals(new OverridenObjectGuide("hola"))));
        out.println("---------------------------");
    }

    //these are default override values which do not change super (superclass) behaviour
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

}
