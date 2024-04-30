package com.example.designpatternintroduction_hiroshi_yuki.J13_visitor.a1;

public class Main {
  public static void main(String[] args) {
    System.out.println("Making root entries...");
    Directory rootdir = new Directory("root");
    Directory bindir = new Directory("bin");
    Directory tmpdir = new Directory("tmp");
    Directory usrdir = new Directory("usr");
    rootdir.add(bindir);
    rootdir.add(tmpdir);
    rootdir.add(usrdir);
    bindir.add(new File("vi", 10000));
    bindir.add(new File("latex", 20000));

    Directory youngjin = new Directory("youngjin");
    Directory gildong = new Directory("gildong");
    Directory dojun = new Directory("dojun");
    usrdir.add(youngjin);
    usrdir.add(gildong);
    usrdir.add(dojun);
    youngjin.add(new File("diary.html", 100));
    youngjin.add(new File("Composite.java", 200));
    gildong.add(new File("memo.tex", 300));
    dojun.add(new File("game.doc", 400));
    dojun.add(new File("junk.mail", 500));

    FileFindVisitor ffv = new FileFindVisitor(".html");
//    rootdir.accept(new ListVisitor());
    rootdir.accept(ffv);

    System.out.println("HTML Files are:");
    for (File file : ffv.getFoundFiles()) {
      System.out.println(file);
    }
  }
}
