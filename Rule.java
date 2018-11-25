public class Rule {
    private String leftArea;
    private String rightArea;

    public Rule(String leftArea, String rightArea) {
        this.leftArea = leftArea;
        this.rightArea = rightArea;
    }

    public String getLeftArea() {
        return leftArea;
    }

    public String getRightArea() {
        return rightArea;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "leftArea='" + leftArea + '\'' +
                ", rightArea='" + rightArea + '\'' +
                '}';
    }
}
