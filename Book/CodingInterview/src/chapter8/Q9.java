package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Q. n쌍의 괄호로 만들 수 있는 합당한 조합 출력
// 가장 직관적 -> ()()() -> 모든 조합-> 경우의 수 너무 많다., 괄호 유효 여부 판단해서 Set에 담음.
public class Q9 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    List<String> result = new ArrayList<>();
    dfs(new StringBuilder(), n, 0, 0, result);

    for (String s : result) {
      System.out.println(s);
    }
  }

  private static void dfs(StringBuilder sb, int length, int open, int close, List<String> result) {
    if (sb.length() == length * 2) {
      result.add(sb.toString());
      return;
    }

    if (open < length) {
      sb.append('(');
      dfs(sb, length, open + 1, close, result);
      sb.deleteCharAt(sb.length() - 1);
    }
    if (close < open)  {
      sb.append(')');
      dfs(sb, length, open, close + 1, result);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
