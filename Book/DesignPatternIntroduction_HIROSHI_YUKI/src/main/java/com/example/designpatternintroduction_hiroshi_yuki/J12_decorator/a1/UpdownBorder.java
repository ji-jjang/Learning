package com.example.designpatternintroduction_hiroshi_yuki.J12_decorator.a1;

public class UpdownBorder extends Border {
 private char borderChar;

  public UpdownBorder(Display display, char ch) {
    super(display);
    this.borderChar = ch;
  }

  @Override
  public int getColumns() {
    return display.getColumns();
  }

  @Override
  public int getRows() {
    return 1 + display.getRows() + 1;
  }

  @Override
  public String getRowText(int row) {
    if (row == 0 || row == getRows() - 1) {
      return makeLine(borderChar, getColumns());
    } else {
      return display.getRowText(row - 1);
    }
  }

  private String makeLine(char ch, int count) {
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < count; i++) {
      line.append(ch);
    }
    return line.toString();
  }
}
