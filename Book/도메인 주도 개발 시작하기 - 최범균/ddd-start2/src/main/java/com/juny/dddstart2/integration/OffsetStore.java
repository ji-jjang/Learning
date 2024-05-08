package com.juny.dddstart2.integration;

public interface OffsetStore {
  long get();
  void update(long nextOffset);
}

