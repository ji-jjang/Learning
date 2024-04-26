package com.core.engineerinformationprocessing.sujebi.J5;

import java.util.HashMap;
import java.util.Map;

public class Q8 {
  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    map.put("Str1", "정보관리기술사");
    map.put("Str2", "정보처리기사");
    map.put("Str3", "빅데이터분석기사");
    map.put("Str4", "정보처리산업기사");
    map.put("Str5", "정보처리기능사");

    for (int i = 0; i < 5; i++) {
      String key = "Str" + Integer.toString(i);
      map.remove(key);
    }
    System.out.println(map);
  }
}
