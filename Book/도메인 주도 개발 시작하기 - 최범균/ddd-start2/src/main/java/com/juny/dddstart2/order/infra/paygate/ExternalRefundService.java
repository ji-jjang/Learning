package com.juny.dddstart2.order.infra.paygate;

import com.juny.dddstart2.order.command.application.RefundService;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExternalRefundService implements RefundService {

  @Override
  public void refund(String orderNumber) {
    log.info("refund order[{}]", orderNumber);
  }
}
