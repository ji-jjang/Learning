package chapter8;

import java.util.HashMap;

public class Q14 {

//  key = false1^0|1
//  left = 1
//  right = 0|1
//
//  key = true0|1
//  left = 0
//  right = 1
//
//  key = false0|1
//  left = 0
//  right = 1
//  left = 1^0
//  right = 1
//
//  key = true1^0
//  left = 1
//  right = 0
//
//  key = false1^0
//  left = 1
//  right = 0
//  ways = 1

  public static void main(String[] args){
    String expression = "1^0|1";
    boolean expected = false;
    HashMap<String, Integer> memo = new HashMap<>();
    int ways = countEval(expression, expected, memo);
    System.out.println("ways = " + ways);
  }

  public static int countEval(String s, boolean result, HashMap<String, Integer> memo) {
    if (s.length() == 0) return 0;
    if (s.length() == 1) {
      boolean singleValue = s.charAt(0) == '1';
      return singleValue == result ? 1 : 0;
    }
    String key = result + s;
    System.out.println();
    System.out.println("key = " + key);
    if (memo.containsKey(key)) return memo.get(key);

    int ways = 0;

    for (int i = 1; i < s.length(); i += 2) {
      char c = s.charAt(i);
      String left = s.substring(0, i);
      String right = s.substring(i + 1);
      System.out.println("left = " + left);
      System.out.println("right = " + right);

      int leftTrue = countEval(left, true, memo);
      int leftFalse = countEval(left, false, memo);
      int rightTrue = countEval(right, true, memo);
      int rightFalse = countEval(right, false, memo);
      int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

      int totalTrue = 0;
      if (c == '^') {
        totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
      } else if (c == '&') {
        totalTrue = leftTrue * rightTrue;
      } else if (c == '|') {
        totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
      }
      int subWays = result ? totalTrue : total - totalTrue;
      ways += subWays;
    }
    memo.put(key, ways);
    return ways;
  }
}
