package DAY_05;

import Reader.FileReader;
import Reader.InputType;
import Utils.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DAY_05_2 {

    private static final String DAY = "05";

    public static void main(String[] args) throws Exception {
        Timer.startTimer();
        String[] input = FileReader.readFileAsString(DAY, InputType.NORMAL).split("[\\r\\n]+");
        int result = 0;

        List<Rule_02> rule02s = new ArrayList<>();
        List<Page_02> page02s = new ArrayList<>();

        for (String str : input) {
            if (str.length() > 6) {
                Page_02 page02 = new Page_02(str, rule02s);
                page02s.add(page02);
            } else {
                Rule_02 rule02 = new Rule_02(Integer.valueOf(str.split("\\|")[0]), Integer.valueOf(str.split("\\|")[1]));
                rule02s.add(rule02);
            }
        }

        List<Page_02> pagesToFix = new ArrayList<>();
        for (Page_02 page02 : page02s) {
            if (!page02.checkRules()) {
                pagesToFix.add(page02);
            }
        }

        for(Page_02 pageToFix : pagesToFix) {
            pageToFix.fixPage();
            result+= pageToFix.getMiddle();
        }

        System.out.println("RESULT: " + result);
        Timer.printEndTime();
    }
}

class Rule_02 {
    int left;
    int right;

    public Rule_02(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " | " + right;
    }
}

class Page_02 {
    List<Integer> pages;
    List<Rule_02> rules = new ArrayList<>();

    public Page_02(String pageLine, List<Rule_02> pages) {
        String[] lines = pageLine.split(",");

        this.pages = Arrays.stream(lines).map(Integer::parseInt).collect(Collectors.toList());
        setRules(pages);
    }

    private void setRules(List<Rule_02> rules) {
        for (Rule_02 rule : rules) {
            if (checkContains(rule)) {
                this.rules.add(rule);
            }
        }
    }

    public void fixPage() {
        List<Rule_02> wrongRules = getCurrentlyWrongRules();

        while (!wrongRules.isEmpty()) {
            Rule_02 rule = wrongRules.getFirst();
            swapNumbers(rule.left, rule.right);
            wrongRules =  getCurrentlyWrongRules();
        }
    }

    private List<Rule_02> getCurrentlyWrongRules() {
        List<Rule_02> wrongRules = new ArrayList<>();
        for (Rule_02 rule02 : this.rules) {
            if (!checkRuleSimple(rule02)) {
                wrongRules.add(rule02);
            }
        }
        return wrongRules;
    }

    private boolean checkRuleSimple(Rule_02 rule02) {
        int leftIndex = getIndex(rule02.left);
        int rightIndex = getIndex(rule02.right);

        if (leftIndex < rightIndex) {
            return true;
        }
        return false;
    }

    private void swapNumbers(int left, int right) {
        int leftIndex = getIndex(left);
        int rightIndex = getIndex(right);
        pages.set(leftIndex, right);
        pages.set(rightIndex, left);
    }


    //--------------------------------------------//
    private boolean checkContains(Rule_02 rule02) {
        int leftIndex = getIndex(rule02.left);
        int rightIndex = getIndex(rule02.right);
        if (leftIndex == -1 || rightIndex == -1) {
            return false;
        }
        return true;
    }


    private int getIndex(Integer numToGetIndex) {
        int index = pages.indexOf(numToGetIndex);
        return index;
    }

    public boolean checkRules() {
        for (Rule_02 rule02 : rules) {
            if (!checkRule(rule02)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRule(Rule_02 rule02) {
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

    public int getMiddle() {
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
