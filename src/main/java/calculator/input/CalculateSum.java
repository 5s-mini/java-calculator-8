package calculator.input;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateSum {

    private final List<Integer> numbers = new ArrayList<>();

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int Process(String strInput) {
        IsNull(strInput);

        if (strInput.isEmpty()) {
            return 0;
        }

        IsBlank(strInput);

        numbers.clear();

        if (strInput.startsWith("//")) {
            IsCustom(strInput);
        } else {
            IsDefault(strInput);
        }

        return Sum();
    }

    void IsBlank(String strInput) {
        if (strInput.isBlank()) {
            throw new IllegalArgumentException("문자열이 없어요.");
        }
    }

    void IsNull(String strInput) {
        if (strInput == null) {
            throw new IllegalArgumentException("문자열이 null이에요.");
        }
    }

    void IsDefault(String strInput) {
        String[] tokens = strInput.split("[,:]");
        for (String token : tokens) {
            Add(token);
        }
    }

    void IsCustom(String strInput) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(strInput);
        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            String numbersPart = matcher.group(2);
            String[] tokens = numbersPart.split(Pattern.quote(customDelimiter));

            for (String token : tokens) {
                Add(token);
            }
        } else {
            throw new IllegalArgumentException("커스텀 구분자 입력이 잘못됐어요.");
        }
    }

    void Add(String token) {
        int number = ParseNum(token);
        IsPositive(number);
        numbers.add(number);
    }

    int ParseNum(String strInput) {
        try {
            return Integer.parseInt(strInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 입력됐어요.");
        }
    }

    void IsPositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수가 입력됐어요.");
        }
    }

    private int Sum() {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}