package ee.taltech.theme2.part6;

import java.util.List;

public class CalculationResult {

    private CalculationStatus status;
    private List<Integer> input;
    private List<Integer> output;
    private Long duration;

    public CalculationResult(CalculationStatus status, List<Integer> input, List<Integer> output, Long duration) {
        this.status = status;
        this.input = input;
        this.output = output;
        this.duration = duration;
    }
}
