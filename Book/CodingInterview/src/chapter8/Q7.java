package chapter8;

import java.util.ArrayList;
import java.util.List;

public class Q7 {

  private static void dfs(StringBuilder sb, String cur, List<String> ans, boolean[] isVisited) {
    if (sb.length() == cur.length()) {
      ans.add(sb.toString());
      return;
    }
    for (int i = 0; i < cur.length(); ++i) {
      if (isVisited[i]) continue;
      sb.append(cur.charAt(i));
      isVisited[i] = true;
      dfs(sb, cur, ans, isVisited);
      sb.deleteCharAt(sb.length() - 1);
      isVisited[i] = false;
    }
  }

  public static void main(String[] args) {
    String str = "abc";

    List<String> ans = new ArrayList<>();
    boolean[] isVisited = new boolean[str.length()];

    dfs(new StringBuilder(), str, ans, isVisited);

    for (var e : ans) {
      System.out.println(e);
    }
  }
}
//public static void main(String[] args) {
//  String str = "ABC";
//  ArrayList<String> ret = new ArrayList<>();
//  dfs(str, new StringBuilder(), ret);
//  for (String e : ret) {
//    System.out.println(e);
//  }
//}
//
//  private static void dfs(String str, StringBuilder cur, ArrayList<String> ret) {
//    System.out.println("str: " + str + " cur: " + cur);
//    if (str.length() == 0) {
//      ret.add(cur.toString());
//      return;
//    }
//    for (int i = 0; i < str.length(); i++) {
//      char ch = str.charAt(i);
//      String remaining = str.substring(0, i) + str.substring(i + 1);
//
//      cur.append(ch);
//      dfs(remaining, cur, ret);
//      cur.deleteCharAt(cur.length() - 1);
//    }
//  }
//}
