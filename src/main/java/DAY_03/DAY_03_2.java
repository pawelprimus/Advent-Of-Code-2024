package DAY_03;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAY_03_2 {

    private static final String DAY = "03";

    private static final String MUL_REGEX = "mul\\((\\d+),(\\d+)\\)";

    private static final String DO = "do\\(\\)";
    private static final String DO_NOT = "don't\\(\\)";
    //private static final String ALL_BETWEEN = DO_NOT + "\\.+" + DO;
    private static final String ALL_BETWEEN = "don't\\((.+?)\\)do\\(\\)";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String input = FileReader.readFileAsString(DAY, InputType.NORMAL);

        // xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))

        Pattern mulPattern = Pattern.compile(ALL_BETWEEN);
        Matcher mulMatcher = mulPattern.matcher(input);

        String copiedInput = new String(input);
        input = input.replaceAll(ALL_BETWEEN, "");
        input = input.replaceAll("don't\\(\\).+", "");

        
//        while (mulMatcher.find()) {
//
//            mulMatcher.group();
//            int startIndex = mulMatcher.start();
//            int endIndex = mulMatcher.end();
//
//            System.out.println(input.substring(startIndex, endIndex));
//        }
//        System.out.println(input);
        System.out.println(input);

        int result = countSum(input);            // 78882157
        System.out.println("RESULT: " + result); // 84011518 TOO LOW       // 122212172 TOO HIGH
        Timer.printEndTime();                    // 67549697
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
