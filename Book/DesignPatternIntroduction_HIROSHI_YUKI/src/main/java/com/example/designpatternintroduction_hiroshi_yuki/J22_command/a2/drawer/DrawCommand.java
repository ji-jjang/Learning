package com.example.designpatternintroduction_hiroshi_yuki.J22_command.a2.drawer;

import com.example.designpatternintroduction_hiroshi_yuki.J22_command.a2.command.Command;

import java.awt.*;

public class DrawCommand implements Command {

  protected Drawable drawable;

  private Point position;

  public DrawCommand(Drawable drawable, Point position) {
    this.drawable = drawable;
    this.position = position;
  }

  @Override
  public void execute() {
    drawable.draw(position.x, position.y);
  }
}
