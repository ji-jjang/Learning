package com.example.designpatternintroduction_hiroshi_yuki.J16_mediator.a1;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ColleagueTextField extends TextField implements TextListener, Colleague {
  private Mediator mediator;

  public ColleagueTextField(String text, int columns) {
    super(text, columns);
  }
  @Override
  public void setMediator(Mediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void setColleagueEnabled(boolean enabled) {
    setEnabled(enabled);
    setBackground(enabled ? Color.white : Color.lightGray);
  }

  @Override
  public void textValueChanged(TextEvent e) {
    mediator.colleagueChanged();
  }
}
