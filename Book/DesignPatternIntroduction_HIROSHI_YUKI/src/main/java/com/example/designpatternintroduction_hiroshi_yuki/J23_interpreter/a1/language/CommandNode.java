package com.example.designpatternintroduction_hiroshi_yuki.J23_interpreter.a1.language;

public class CommandNode extends Node {
  private Node node;

  @Override
  public void parse(Context context) throws ParseException {
    if (context.currentToken().equals("repeat")) {
      node = new RepeatCommandNode();
      node.parse(context);
    } else {
      node = new PrimitiveCommandNode();
      node.parse(context);
    }
  }

  @Override
  public void execute() {
    node.execute();
  }

  @Override
  public String toString() {
    return node.toString();
  }
}
