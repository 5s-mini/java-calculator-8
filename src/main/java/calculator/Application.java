package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String str_input = Console.readLine();
        int result = add(str_input);
        System.out.println("결과 : " + result);
    }

    private static int add(String str_input) {
        if (str_input == null || str_input.isEmpty()) {
            return 0;
        }


    }
}