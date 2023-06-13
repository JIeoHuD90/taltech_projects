package ee.taltech.theme2.part6;

import java.util.List;

public class Negatives {

    //todo 16.1 add tests and add logic
    // sometimes you want to return more logic from your method than just a list of numbers
    // in that case in OOP world we use another Object to wrap around it
    public static CalculationResult removePositives(List<Integer> numbers) {
        long startTime = System.currentTimeMillis();
        return new CalculationResult(CalculationStatus.ERROR, numbers, numbers, startTime);
    }
}
