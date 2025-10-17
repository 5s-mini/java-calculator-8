package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String strInput = Console.readLine();
        int result = calcSum(strInput);
        System.out.println("결과 : " + result);
    }

    private static int calcSum(String strInput) {
        if (strInput == null || strInput.isEmpty()) {
            return 0;
        }

        String normalized = strInput.replace("\\n", "\n");
        String[] tokens = splitToTokens(normalized);
        int sum = 0;

        for (String t : tokens) {
            if (t == null) {
                throw new IllegalArgumentException("빈 숫자 토큰이 존재합니다.");
            }

            int value = getValue(t);
            sum += value;
        }

        return sum;
    }

    private static int getValue(String t) {
        String trimmed = t.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("빈 숫자 항목이 존재합니다.");
        }

        int value;
        try {
            value = Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다: " + trimmed);
        }

        if (value < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + trimmed);
        }
        return value;
    }

    private static String[] splitToTokens(String input) {

    }
}