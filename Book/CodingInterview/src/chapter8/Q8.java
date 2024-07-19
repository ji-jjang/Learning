package chapter8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Q. 문자열 중복되지 않은 순열. 문자는 중복
public class Q8 {
  // 1. 정답 배열을 Set에 옮겨닮으면 된다.
  // 2. 만약 문자열 길이가 20이라면? 단, 문자열 중 하나가 중복된 문자가 10개가 있다면?
  private static void dfs(StringBuilder sb, char[] cur, List<String> ans, boolean[] isVisited) {
    if (sb.length() == cur.length) {
      ans.add(sb.toString());
      return;
    }
    for (int i = 0; i < cur.length; ++i) {
      if (isVisited[i]) continue;
      if (i > 0 && cur[i] == cur[i - 1] && !isVisited[i - 1]) {
        continue;
      }
      sb.append(cur[i]);
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

    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    dfs(new StringBuilder(), chars, ans, isVisited);

    for (var e : ans) {
      System.out.println(e);
    }
    System.out.println("ans.size() = " + ans.size());
  }
}
