package com.example.designpatternintroduction_hiroshi_yuki.J23_interpreter;

public class PrimitiveCommandNode extends Node {
  private String name;

  @Override
  public void parse(Context context) throws ParseException {
    name = context.currentToken();
    if (name == null) {
      throw new ParseException("Error: Missing <primitive command>>");
    } else if (!name.equals("go") && !name.equals("right") && !name.equals("left")) {
      throw new ParseException("Error: unknown <primitive command>: '" + name + "'");
    }
    context.skipToken(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
