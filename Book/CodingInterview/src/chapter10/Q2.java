package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2 {

  // "pong", "cba", "gpon", "bac",  "bca", "cab"

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] words = br.readLine().split(", ");

    List<String> ws = Arrays.asList(words);
    List<String> ans = sortAnagrams(ws);
    System.out.println(ans);
  }

  static List<String> sortAnagrams(List<String> words) {

    Map<String, List<String>> anagrams = new HashMap<>();

    for (String word : words) {

      char[] charArray = word.toCharArray();
      Arrays.sort(charArray);
      String sortedWord = new String(charArray);

      if (!anagrams.containsKey(sortedWord)) {
        anagrams.put(sortedWord, new ArrayList<>());
      }
      anagrams.get(sortedWord).add(word);
    }

    List<String> result = new ArrayList<>();
    for (List<String> group : anagrams.values()) {
      result.addAll(group);
    }

    return result;
  }
}
