package DAY_03;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.Colours.*;

public class DAY_03_2 {

    private static final String DAY = "03";

    private static final String MUL_REGEX = "mul\\((\\d+),(\\d+)\\)";

    private static final String DO = "do\\(\\)";
    private static final String DO_NOT = "don't\\(\\)";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String input = FileReader.readFileAsString(DAY, InputType.NORMAL);

        List<Integer> doList = matcherToIndexList(input, DO);
        List<Integer> doNotList = matcherToIndexList(input, DO_NOT);

        boolean isEnabled = true;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            print(isEnabled, input.charAt(i));
            if(i %200 == 0){
                System.out.println();
            }
            // add only if is enabled
            if (isEnabled) {
                sb.append(input.charAt(i));
            }

            if (isEnabled) {
                if (doNotList.contains(i)) {
                    i+=4;
                    isEnabled = false;
                }
            } else {
                if (doList.contains(i)) {
                    isEnabled = true;
                }
            }

        }

        int result = countSum(sb.toString());
        System.out.println("RESULT: " + result); //106921067
        Timer.printEndTime();
    }

    private static List<Integer> matcherToIndexList(String input , String regex){
        List<Integer> indexes = new ArrayList<>();
        Pattern doNotPattern = Pattern.compile(regex);
        Matcher dNotoMatcher = doNotPattern.matcher(input);

        while (dNotoMatcher.find()) {
            dNotoMatcher.group();
            int startIndex = dNotoMatcher.start();
            indexes.add(startIndex);
        }
        return indexes;
    }

    public static void print(boolean isEnabled, char c) {
        if (isEnabled) {
            System.out.print(ANSI_GREEN  + c  + ANSI_RESET);
        } else {
            System.out.print(ANSI_RED + c + ANSI_RESET);
        }
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
