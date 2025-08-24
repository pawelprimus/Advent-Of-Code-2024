package DAY_04;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

public class DAY_04_1 {

    private static final String DAY = "04";

    private static int MAX_X;
    private static int MAX_Y;

    private static final String XMAS = "XMAS";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String[] input = FileReader.readFileAsString(DAY, InputType.NORMAL).split("[\\r\\n]+");
        int result = 0;

        char[][] signs = new char[input.length][input[0].length()];

        for (int i = 0; i < input.length; i++) {
            String inputLine = input[i];
            for (int j = 0; j < inputLine.length(); j++) {
                signs[i][j] = inputLine.charAt(j);
                System.out.print(inputLine.charAt(j) + " ");
            }
            System.out.println();
        }
        System.out.println();

        MAX_X = signs[0].length;
        MAX_Y = signs.length;

        for (int y = 0; y < signs.length; y++) {
            for (int x = 0; x < signs[y].length; x++) {

                if (signs[y][x] == 'X') {
                    result += xmasCheck(y, x, signs);
                }

                System.out.print(signs[y][x] + " ");
            }
            System.out.println();
        }

        System.out.println("RESULT: " + result); // 2567
        Timer.printEndTime();
    }

    private static int xmasCheck(int currentY, int currentX, char[][] signs) {
        int xmasCounter = 0;

        StringBuilder toCheck = new StringBuilder("X");
        // GO up
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY + i, currentX, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");


        // GO down
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY - i, currentX, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");

        // GO right
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY, currentX + i, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");

        // GO left
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY, currentX - i, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");

        // GO LEFT UP
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY + i, currentX - i, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");

        // GO RIGHT UP
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY + i, currentX + i, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");
        // GO LEFT DOWN
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY - i, currentX - i, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }
        toCheck = new StringBuilder("X");
        // GO RIGHT DOWN
        for (int i = 1; i <= 3; i++) {
            toCheck.append(getCharAt(currentY - i, currentX + i, signs));
        }
        if (toCheck.toString().equals(XMAS)) {
            xmasCounter++;
        }

        return xmasCounter;
    }

    private static char getCharAt(int currentY, int currentX, char[][] signs) {
        if (currentY < 0 || currentY >= MAX_Y) {
            return ' ';
        }
        if (currentX < 0 || currentX >= MAX_X) {
            return ' ';
        }
        return signs[currentY][currentX];
    }

}
