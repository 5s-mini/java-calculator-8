package calculator.input;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CalculateSum {

    private static final String DEFAULT_DELIMITERS = "[,:]";

    public int process(String strInput) {
        validateNull(strInput);

        if (strInput.isEmpty()) {
            return 0;
        }

        validateBlank(strInput);
        List<Integer> numbers = parseNumbers(strInput);
        return sum(numbers);
    }

    private List<Integer> parseNumbers(String strInput) {
        if (strInput.startsWith("//")) {
            return parseCustomDelimiterInput(strInput);
        }
        return parseDefaultInput(strInput);
    }

    private List<Integer> parseDefaultInput(String strInput) {
        String[] tokens = strInput.split(DEFAULT_DELIMITERS);
        return convertTokensToNumbers(tokens);
    }

    private List<Integer> parseCustomDelimiterInput(String strInput) {
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
        validateCustomDelimiter(customDelimiter);

        String numbersPart = strInput.substring(newlineIndex + delimiterLength);
        String[] tokens = numbersPart.split(Pattern.quote(customDelimiter));
        return convertTokensToNumbers(tokens);
    }

    private void validateCustomDelimiter(String customDelimiter) {
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자 입력이 잘못됐어요.");
        }
    }

    private List<Integer> convertTokensToNumbers(String[] tokens) {
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            int number = parseNum(token);
            validatePositive(number);
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

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수가 입력됐어요.");
        }
    }

    private void validateBlank(String strInput) {
        if (strInput.isBlank()) {
            throw new IllegalArgumentException("문자열이 없어요.");
        }
    }

    private void validateNull(String strInput) {
        if (strInput == null) {
            throw new IllegalArgumentException("문자열이 null이에요.");
        }
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}