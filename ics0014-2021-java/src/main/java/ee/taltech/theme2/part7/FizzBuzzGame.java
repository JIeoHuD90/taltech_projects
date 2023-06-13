package ee.taltech.theme2.part7;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzzGame {

    public static void main(String[] args) {

        //todo 17.1 fizzbuzz is a drinking game
        // hope your version will be as fun
        // rules are as follows:
        // numbers 1-100
        // if it divides by 3 you see fizz
        // if it divides by 5 you see buzz
        // if it divides by 3 and 5 at the same time you say fizzbuzz
        List<Integer> numbers = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());


        //todo 17.2 try to solve it in different ways
    }
}
