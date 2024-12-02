package DAY_02;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class DAY_02_1 {

    private static final String DAY = "02";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String[] input = FileReader.readFileAsString(DAY, InputType.NORMAL).split("[\\r\\n]+");
        int result = 0;
        for (String string : input) {
            String[] splited = string.split("\\s+");

            List<Integer> levels = new ArrayList<>();
            for (String splitedString : splited) {
                String[] numbers = splitedString.split("\\s+");
                for (String strNum : numbers) {
                    int num = Integer.parseInt(strNum);
                    levels.add(num);
                }
            }
            if (isIncrease(levels)) {
                result += isIncreaseCorrectly(levels) ? 1 : 0;
            } else {
                result += isDecreaseCorrectly(levels) ? 1 : 0;
            }
        }

        System.out.println("RESULT: " + result); // 402
        Timer.printEndTime();
    }

    private static boolean isIncrease(List<Integer> levels) {
        int firstLevel = levels.getFirst();

        for (Integer loopLevel : levels) {
            if (firstLevel != loopLevel) {
                return loopLevel > firstLevel;
            }
        }
        return true;
    }

    private static boolean isIncreaseCorrectly(List<Integer> levels) {
        Integer[] primitveArray = levels.toArray(new Integer[0]);

        for (int i = 1; i < primitveArray.length; i++) {
            int previousLevel = primitveArray[i - 1];
            int currentLevel = primitveArray[i];
            int diff = currentLevel - previousLevel;

            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreaseCorrectly(List<Integer> levels) {
        Integer[] primitiveArray = levels.toArray(new Integer[0]);

        for (int i = 1; i < primitiveArray.length; i++) {
            int previousLevel = primitiveArray[i - 1];
            int currentLevel = primitiveArray[i];
            int diff = currentLevel - previousLevel;

            if (diff > -1 || diff < -3) {
                return false;
            }
        }
        return true;
    }

}
