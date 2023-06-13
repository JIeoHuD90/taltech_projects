package ee.taltech.theme1.part2;

import ee.taltech.theme1.part2.celestialbodies.Sun;

public class HelloUniverse {

    //todo 2.0 create a main method
    //IntelliJ protip psvm and tab

    //todo 2.1 find a way to print out Sun's name (Sun is in celestialbodies package)
    //todo 2.2 find a way to print out Earth's name (Earth is in celestialbodies package)
    //todo 2.3 programmers don't like public fields,
    // can you find a way to make Sun's and Earth's names private while still getting desired printout
    //todo 2.4 when you print out new Sun() it returns something like ee.taltech.lesson1.part2.Sun@5674cd4d
    // is there a way to make that action print our something else like "Sun"
    public static void main(String[] args) {
        System.out.println(new Sun());
    }
}
