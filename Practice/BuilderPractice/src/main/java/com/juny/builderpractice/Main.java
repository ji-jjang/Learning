package com.juny.builderpractice;

public class Main {

  public static void main(String[] args){
    TextBuilder textbuilder = new TextBuilder();
    Director director = new Director(textbuilder);
    director.construct();
    String result = textbuilder.getTextResult();
    System.out.println(result);

    HTMLBuilder htmlbuilder = new HTMLBuilder();
    director = new Director(htmlbuilder);
    director.construct();
    String filename = htmlbuilder.getHTMLResult();
    System.out.println("HTML파일 " + filename + "이 작성되었습니다.");
  }
}
