package com.example.designpatternintroduction_hiroshi_yuki.J22_command.a1.drawer;

import com.example.designpatternintroduction_hiroshi_yuki.J22_command.a1.command.Command;

import java.awt.*;

public class ColorCommand implements Command {
  protected Drawable drawable;

  private Color color;

  public ColorCommand(Drawable drawable, Color color) {
    this.drawable = drawable;
    this.color = color;
  }

  @Override
  public void execute() {
    drawable.setColor(color);
  }
}
