package com.juny.dddstart2.order.command.domain;

public interface CancelPolicy {
  boolean hasCancellationPermission(Order order, Canceller canceller);
}
