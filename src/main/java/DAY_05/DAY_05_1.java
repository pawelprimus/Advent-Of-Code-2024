package DAY_05;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.*;
import java.util.stream.Collectors;

public class DAY_05_1 {

    private static final String DAY = "05";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String[] input = FileReader.readFileAsString(DAY, InputType.NORMAL).split("[\\r\\n]+");
        int result = 0;

        List<Rule> rule02s = new ArrayList<>();
        List<Page> page02s = new ArrayList<>();

        for (String str : input) {
            if (str.length() > 6) {
                Page page02 = new Page(str);
                page02s.add(page02);
            } else {
                Rule rule02 = new Rule(Integer.valueOf(str.split("\\|")[0]), Integer.valueOf(str.split("\\|")[1]));
                rule02s.add(rule02);
            }
        }

        for (Page page02 : page02s) {
            result += page02.checkRules(rule02s);
            System.out.println(page02);
        }


        System.out.println("RESULT: " + result); // 7307
        Timer.printEndTime();
    }
}

class Rule {
    int left;
    int right;

    public Rule(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " | " + right;
    }
}

class Page {
    List<Integer> pages;

    public Page(String pageLine) {
        String[] lines = pageLine.split(",");

        this.pages = Arrays.stream(lines).map(Integer::parseInt).collect(Collectors.toList());
    }

    public int checkRules(List<Rule> rule02s) {
        for (Rule rule02 : rule02s) {
            if (!checkRule(rule02)) {
                return 0;
            }
        }
        return getMiddle();
    }

    private boolean checkRule(Rule rule02) {
        int leftIndex = getIndex(rule02.left);
        int rightIndex = getIndex(rule02.right);
        if (leftIndex == -1 || rightIndex == -1) {
            return true;
        }

        if (leftIndex < rightIndex) {
            return true;
        }
        return false;
    }

    private int getIndex(Integer numToGetIndex) {
        int index = pages.indexOf(numToGetIndex);
        return index;
    }

    private int getMiddle() {
        return pages.get(pages.size() / 2);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pages.size(); i++) {
            builder.append(pages.get(i)).append(",");
        }

        return builder.toString();
    }
}
