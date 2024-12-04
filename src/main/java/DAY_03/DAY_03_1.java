package DAY_03;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAY_03_1 {

    private static final String DAY = "03";

    private static final String MUL_REGEX = "mul\\((\\d+),(\\d+)\\)";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String input = FileReader.readFileAsString(DAY, InputType.NORMAL);

        int result = countSum(input);

        System.out.println("RESULT: " + result); // 174561379
        Timer.printEndTime();
    }

    private static int countSum(String string) {
        Pattern mulPattern = Pattern.compile(MUL_REGEX);
        Matcher mulMatcher = mulPattern.matcher(string);
        int sum = 0;
        while (mulMatcher.find()) {

            int firstNumber = Integer.parseInt(mulMatcher.group(1));
            int secondNumber = Integer.parseInt(mulMatcher.group(2));
            sum += firstNumber * secondNumber;
        }
        return sum;
    }
}
