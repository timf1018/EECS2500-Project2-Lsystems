public class LSystem {

    private Rule[] rules;
    private double angleIncrement;
    private int iterationCount;
    private String startValue;
    private Queue resultsQueue;

    public LSystem(Rule[] rules, double angleIncrement, int iterationCount, String startValue) {
        this.rules = rules;
        this.angleIncrement = angleIncrement;
        this.iterationCount = iterationCount;
        this.startValue = startValue;
        this.resultsQueue = new Queue(20);
    }

    public void setRules(Rule[] rules) {
        this.rules = rules;
    }

    public void setAngleIncrement(double angleIncrement) {
        this.angleIncrement = angleIncrement;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getResult() {
        // put start value on results queue
        char[] chars = this.startValue.toCharArray();
        for (int i=0, n = chars.length; i < n; i++) {
            String symbol = Character.toString(chars[i]);
            resultsQueue.add(symbol);
        }
        resultsQueue.add("/");
        // start expansion process
        this.performExpansion();
        // Build final result string
        String result = "";
        String currentSymbol;
        while (!(currentSymbol = resultsQueue.remove()).equals("/")) {
            result = result + currentSymbol;
        }
        return result;
    }

    public void performExpansion() {
        String currentSymbol;
        while (!(currentSymbol = resultsQueue.remove()).equals("/")) {
            boolean charMatched = false;
            for (int j = 0; j < rules.length; j++) {

                if (rules[j].getLeftArea() == null || rules[j].getLeftArea().trim().equals("")) {
                    continue;
                }
                if (rules[j].getRightArea() == null || rules[j].getRightArea().trim().equals("")) {
                    continue;
                }

                Rule compareRule = rules[j];
                char[] leftChar = compareRule.getLeftArea().toCharArray();
                char d = leftChar[0];
                if (currentSymbol.equals(Character.toString(d))) {
                    for(int i = 0; i<compareRule.getRightArea().length();i++){
                        resultsQueue.add(Character.toString(compareRule.getRightArea().toString().charAt(i)));
                    }
                    charMatched = true;
                }
                else {
                    System.out.println("no rule match");
                }
            }
            if (!charMatched) {
                if (("+-[]").contains(currentSymbol)) {
                    resultsQueue.add(currentSymbol);
                } else {
                    throw new IllegalArgumentException("Invalid Character");
                }
            }
        }
        resultsQueue.add("/");

        this.iterationCount--;
        if (this.iterationCount > 0) {
            this.performExpansion();
        }
    }

}
