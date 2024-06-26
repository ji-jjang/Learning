package com.juny.dddstart2.order.infra;

import com.juny.dddstart2.order.command.application.RefundService;
import com.juny.dddstart2.order.command.domain.OrderCanceledEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class OrderCanceledEventHandler {
  private RefundService refundService;

  public OrderCanceledEventHandler(RefundService refundService) {
    this.refundService = refundService;
  }

  @Async
  @TransactionalEventListener(
      classes = OrderCanceledEvent.class,
      phase = TransactionPhase.AFTER_COMMIT
  )
  public void handle(OrderCanceledEvent event) {
    refundService.refund(event.getOrderNumber());
  }
}
