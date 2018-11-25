
public class LSystem {

  private Array rules;
  private int angleIncrement;
  private int iterationCount;
  private String startValue;

  public LSystem(Array rules, int angleIncrement, int interations, String startValue) {
    this.rules = rules;
    this.angleIncrement = angleIncrement;
    this.iterationCount = iterationCount;
    this.startValue = startValue;
  }

  public String getResult() {
    return this.performExpansion(this.startValue);
  }

  public String performExpansion(inputValue) {
      String result = "";
      char[] chars = inputValue.toCharArray();
      for (int i = 0, n = chars.length; i < n; i++) {
          char c = chars[i];
          //  System.out.println("comparing" + c);
          // create another loop to iterate through rules
          boolean charMatched = false;
          for (int j = 0; j < 5; j++) {
              Rule compareRule = rules.remove();
              //Rule compareRule = new Rule(rules.remove().getLeftArea(),rules.remove().getRightArea());
              char[] leftChar = compareRule.getLeftArea().toCharArray();
              char d = leftChar[0];
              //  System.out.println("Rules being compared:" + d);
              if (c == d) {
                  // System.out.println(c + "=" + d);
                  // matches this rule, so expand by replacing with rule right side.
                  // System.out.println(rhs[j].getText().trim().toUpperCase());
                  result = result + compareRule.getRightArea();

                  charMatched = true;
              } else {
                  // unchanged char value from input
                  //result = result + c;
              }
              //System.out.println(result);
              rules.add(compareRule);

          }
          if (!charMatched) {
              result = result + c;
          }
          charMatched = false;
      }
      this.iterationCount--;
      if (this.iterationCount > 0) {
          result = this.expansion(result, this.iterationCount,rules);
      }
      return result;
  }


}
