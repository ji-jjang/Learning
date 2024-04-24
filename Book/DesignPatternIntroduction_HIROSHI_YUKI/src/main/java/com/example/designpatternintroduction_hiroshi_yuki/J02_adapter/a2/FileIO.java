package com.example.designpatternintroduction_hiroshi_yuki.J02_adapter.a2;

import java.io.IOException;

public interface FileIO {
  public void readFromFile(String filename) throws IOException;

  public void writeToFile(String filename) throws IOException;

  public void setValue(String key, String value);

  public String getValue(String key);
}
