package calculator.input;

import calculator.view.CalculateOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CalculateSum {

    private static final String DEFAULT_DELIMITERS = "[,:]";

    private final CalculateOutput calculateOutput;

    public CalculateSum() {
        this.calculateOutput = new CalculateOutput();
    }

    public void ProcessAndOutput(String strInput) {
        int result = Process(strInput);
        calculateOutput.Output(result);
    }

    public int Process(String strInput) {
        ValidateNull(strInput);
        ValidateBlank(strInput);
        List<Integer> numbers = ParseNumbers(strInput);
        return Sum(numbers);
    }

    private List<Integer> ParseNumbers(String strInput) {
        if (strInput.startsWith("//")) {
            return ParseCustomDelimiterInput(strInput);
        }
        return ParseDefaultInput(strInput);
    }

    private List<Integer> ParseDefaultInput(String strInput) {
        String[] tokens = strInput.split(DEFAULT_DELIMITERS);
        return ConvertTokensToNumbers(tokens);
    }

    private List<Integer> ParseCustomDelimiterInput(String strInput) {
        int newlineIndex = strInput.indexOf('\n');
        int delimiterLength = 1;

        if (newlineIndex < 0) {
            newlineIndex = strInput.indexOf("\\n");
            delimiterLength = 2;
        }
        if (newlineIndex < 0) {
            throw new IllegalArgumentException("커스텀 구분자 입력이 잘못됐어요.");
        }

        String customDelimiter = strInput.substring(2, newlineIndex);
        ValidateCustomDelimiter(customDelimiter);

        String numbersPart = strInput.substring(newlineIndex + delimiterLength);
        String[] tokens = numbersPart.split(Pattern.quote(customDelimiter));
        return ConvertTokensToNumbers(tokens);
    }

    private void ValidateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자 입력이 잘못됐어요.");
        }
    }

    private List<Integer> ConvertTokensToNumbers(String[] tokens) {
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            int number = parseNum(token);
            ValidatePositive(number);
            numbers.add(number);
        }
        return numbers;
    }

    private int parseNum(String strInput) {
        try {
            return Integer.parseInt(strInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 입력됐어요.");
        }
    }

    private void ValidatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수가 입력됐어요.");
        }
    }

    private void ValidateBlank(String strInput) {
        if (strInput.isBlank()) {
            throw new IllegalArgumentException("문자열이 없어요.");
        }
    }

    private void ValidateNull(String strInput) {
        if (strInput == null) {
            throw new IllegalArgumentException("문자열이 null이에요.");
        }
    }

    private int Sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}