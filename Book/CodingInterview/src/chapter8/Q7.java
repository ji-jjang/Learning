package chapter8;

import java.util.ArrayList;
import java.util.List;

public class Q7 {

  private static void dfs(
      int depth,
      List<String> ret,
      String cur,
      StringBuilder sb,
      boolean[] isVisited
      ) {
    if (cur.length() == depth) {
      ret.add(sb.toString());
      return;
    }
    for (int i = 0; i < cur.length(); ++i) {
      if (isVisited[i]) continue;
      sb.setCharAt(depth, cur.charAt(i));
      isVisited[i] = true;
      dfs(depth + 1, ret, cur, sb, isVisited);
      isVisited[i] = false;
    }
  }

  public static void main(String[] args) {
    String str = "abc";

    List<String> ret = new ArrayList<>();
    StringBuilder sb = new StringBuilder(str);
    boolean[] checker = new boolean[str.length()];

    dfs(0, ret, str, sb, checker);

    for (var e : ret) {
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
