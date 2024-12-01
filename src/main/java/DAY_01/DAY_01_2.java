package DAY_01;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class DAY_01_2 {

    private static final String DAY = "01";

    public static void main(String[] args) throws Exception {

        String[] input = FileReader.readFileAsString(DAY, InputType.NORMAL).split("[\\r\\n]+");
        List<Integer> leftList = new ArrayList<>();

        int[] rightPart = new int[100000];

        for (String string : input) {
            String[] splited = string.split("\\s+");
            int leftNum = Integer.parseInt(splited[0]);
            int rightNum = Integer.parseInt(splited[1]);

            leftList.add(leftNum);
            rightPart[rightNum]++;
        }
        int sum = 0;
        for (int num : leftList) {
            sum += num * rightPart[num];
        }

        Timer.stoper();
        System.out.println("RESULT: " + sum); // 18934359
    }
}
