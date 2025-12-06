package calculator.input;

import camp.nextstep.edu.missionutils.Console;

public class StringInput {

    private final CalculateSum calculateSum;

    public StringInput() {
        this.calculateSum = new CalculateSum();
    }

    public void Start() {
        String userInput = Input();
        calculateSum.ProcessAndOutput(userInput);
    }

    public String Input() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }
}