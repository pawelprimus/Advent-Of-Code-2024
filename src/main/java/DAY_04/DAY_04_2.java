package DAY_04;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

public class DAY_04_2 {

    private static final String DAY = "04";

    private static int MAX_X;
    private static int MAX_Y;

    private static final String MAS = "MAS";
    private static final String SAM = "SAM";

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

                if (signs[y][x] == 'A') {
                    result += xmasCheck(y, x, signs) ? 1 : 0;
                }

                System.out.print(signs[y][x] + " ");
            }
            System.out.println();
        }

        System.out.println("RESULT: " + result); // 2029
        Timer.printEndTime();
    }

    private static boolean xmasCheck(int currentY, int currentX, char[][] signs) {

        // UP LEFT to DOWN RIGHT
        StringBuilder toCheck_1 = new StringBuilder();
        toCheck_1.append(getCharAt(currentY + 1, currentX - 1, signs));
        toCheck_1.append("A");
        toCheck_1.append(getCharAt(currentY - 1, currentX + 1, signs));

        // DOWN LEFT to UP RIGHT
        StringBuilder toCheck_2 = new StringBuilder();
        toCheck_2.append(getCharAt(currentY - 1, currentX - 1, signs));
        toCheck_2.append("A");
        toCheck_2.append(getCharAt(currentY + 1, currentX + 1, signs));

        return isMas(toCheck_1.toString()) && isMas(toCheck_2.toString());
    }

    private static boolean isMas(String wordToCheck) {
        return wordToCheck.equals(MAS) || wordToCheck.equals(SAM);
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
