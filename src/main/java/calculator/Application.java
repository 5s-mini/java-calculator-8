package calculator;

import calculator.input.CalculateSum;
import calculator.input.StringInput;
import calculator.view.CalculateOutput;

public class Application {
    public static void main(String[] args) {
        StringInput input = new StringInput();
        CalculateSum calculateSum = new CalculateSum();
        CalculateOutput output = new CalculateOutput();

        try {
            String userInput = input.input();
            int result = calculateSum.process(userInput);
            output.output(result);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}