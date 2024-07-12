package chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//i = 1:
//j = 0: boxes[1] 위에 boxes[0]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//i = 2:
//j = 0: boxes[2] 위에 boxes[0]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//j = 1: boxes[2] 위에 boxes[1]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//i = 3:
//j = 0: boxes[3] 위에 boxes[0]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//j = 1: boxes[3] 위에 boxes[1]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//j = 2: boxes[3] 위에 boxes[2]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//i = 4:
//j = 0: boxes[4] 위에 boxes[0]을 쌓을 수 있음 (너비와 깊이 조건 만족)
//dp[4] = Math.max(dp[4], dp[0] + boxes[4][1])
//dp[4] = Math.max(2, 6 + 2) = 8
//j = 1: boxes[4] 위에 boxes[1]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//j = 2: boxes[4] 위에 boxes[2]을 쌓을 수 없음 (너비와 깊이 조건 불만족)
//j = 3: boxes[4] 위에 boxes[3]을 쌓을 수 없음 (너비와 깊이 조건 불만족)

// 박스 쌓기
//5
//3 2 5
//1 2 4
//4 4 4
//2 3 3
//5 6 7

// ans = 13
// 2 3 3
// 4 4 4
// 5 6 7

//5
//3 2 5
//1 2 4
//1 4 1
//2 3 3
//5 6 7

// ans = 10

public class Q13 {

  // dp[i]: i번째 박스를 맨 위로 했을 때의 최대 높이
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[][] boxes = new int[n + 4][3];

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      boxes[i] = new int[]{w, h, d};
    }

    Arrays.sort(boxes, (o1, o2) -> Integer.compare(o2[1], o1[1]));

    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = boxes[i][1];
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (boxes[i][0] < boxes[j][0] &&
          boxes[i][2] < boxes[j][2]) {
          dp[i] = Math.max(dp[i], dp[j] + boxes[i][1]);
        }
      }
    }

    int ans = 0;
    for (int height : dp) {
      ans = Math.max(ans, height);
    }
    System.out.println("ans = " + ans);
  }
}
