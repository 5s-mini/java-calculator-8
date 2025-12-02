package calculator;

import calculator.input.CalculateSum;
import calculator.input.StringInput;
import calculator.view.CalculateOutput;

public class Application {
    public static void main(String[] args) {
        StringInput input = new StringInput();
        CalculateSum calculateSum = new CalculateSum();
        CalculateOutput output = new CalculateOutput();

        String userInput = input.Input();
        int result = calculateSum.Process(userInput);
        output.Output(result);
    }
}