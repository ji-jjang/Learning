package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ball
// {"at","","","","ball", "", "","car","","","dad","",""}
public class Q5 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String target = br.readLine();
    String str = br.readLine();
    String[] words = str.substring(1, str.length() - 1).split(",");
    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].trim().replaceAll("\"", "");
    }

    int ans = search(words, target, 0, words.length - 1);
    System.out.println(ans);
  }

  private static int search(String[] words, String target, int left, int right) {

    while (left <= right) {
      int mid = (left + right) >>> 1;

      if (words[mid].isEmpty()) {
        int l = mid - 1;
        int r = mid + 1;
        while (true) {
          if (l < left && r > right) {
            return -1;
          } else if (r <= right && !words[r].isEmpty()) {
            mid = r;
            break;
          } else if (l >= left && !words[l].isEmpty()) {
            mid = l;
            break;
          }
          ++r;
          --l;
        }
      }

      if (words[mid].equals(target)) {
        return mid;
      } else if (words[mid].compareTo(target) < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }
}
