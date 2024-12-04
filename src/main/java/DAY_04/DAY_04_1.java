package DAY_04;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

public class DAY_04_1 {

    private static final String DAY = "0X";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String[] input = FileReader.readFileAsString(DAY, InputType.TEST).split("[\\r\\n]+");
        String result = "";

        System.out.println("RESULT: " + result);
        Timer.printEndTime();
    }
}
