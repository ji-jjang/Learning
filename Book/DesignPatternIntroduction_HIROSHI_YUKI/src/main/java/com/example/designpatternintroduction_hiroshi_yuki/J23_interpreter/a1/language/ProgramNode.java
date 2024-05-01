package com.example.designpatternintroduction_hiroshi_yuki.J23_interpreter.a1.language;

public class ProgramNode extends Node {
  private Node commandListNode;

  @Override
  public void parse(Context context) throws ParseException {
    context.skipToken("program");
    commandListNode = new CommandListNode();
    commandListNode.parse(context);
  }

  @Override
  public void execute() {
    commandListNode.execute();
  }

  @Override
  public String toString() {
    return "[program " + commandListNode + "]";
  }
}
