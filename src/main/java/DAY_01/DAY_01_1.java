package DAY_01;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class DAY_01_1 {

    private static final String DAY = "01";

    public static void main(String[] args) throws Exception {

        String[] input = FileReader.readFileAsString(DAY, InputType.NORMAL).split("[\\r\\n]+");
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (String string : input) {
            String[] splited = string.split("\\s+");
            leftList.add(Integer.valueOf(splited[0]));
            rightList.add(Integer.valueOf(splited[1]));
        }

        Integer[] sortedLeftList = leftList.stream().sorted().toArray(Integer[]::new);
        Integer[] sortedRightList = rightList.stream().sorted().toArray(Integer[]::new);

        int distancesSum = 0;
        for (int i = 0; i < sortedLeftList.length; i++) {
            distancesSum += Math.abs(sortedLeftList[i] - sortedRightList[i]);
        }

        System.out.println("RESULT: " + distancesSum); // 2378066
    }
}
