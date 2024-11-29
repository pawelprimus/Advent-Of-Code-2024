package DAY_01;

import Reader.FileReader;
import Reader.InputType;

public class DAY_01_1 {

    private static final String DAY = "0X";

    public static void main(String[] args) throws Exception {

        String[] input = FileReader.readFileAsString(DAY, InputType.TEST).split("[\\r\\n]+");
        String result = "";

        System.out.println("RESULT: " + result);


    }
}
