package com.example.designpatternintroduction_hiroshi_yuki.J22_command.drawer;

import com.example.designpatternintroduction_hiroshi_yuki.J22_command.command.MacroCommand;
import com.example.designpatternintroduction_hiroshi_yuki.J22_command.drawer.Drawable;

import java.awt.*;

public class DrawCanvas extends Canvas implements Drawable {

  private Color color = Color.red;

  private int radius = 6;

  private MacroCommand history;

  public DrawCanvas(int width, int height, MacroCommand history) {
    setSize(width, height);
    setBackground(Color.white);
    this.history = history;
  }

  @Override
  public void paint(Graphics g) {
    history.execute();
  }
  @Override
  public void draw(int x, int y) {
    Graphics g = getGraphics();
    g.setColor(color);
    g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

  }
}
