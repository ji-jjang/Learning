package com.juny.dddstart2.order.infra.domain;

import com.juny.dddstart2.order.command.domain.CancelPolicy;
import com.juny.dddstart2.order.command.domain.Canceller;
import com.juny.dddstart2.order.command.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class SecurityCancelPolicy implements CancelPolicy {
  @Override
  public boolean hasCancellationPermission(Order order, Canceller canceller) {
    return isCancellerOrderer(order, canceller) || isCurrentUserAdminRole();
  }

  private boolean isCancellerOrderer(Order order, Canceller canceller) {
    return order.getOrderer().getMemberId().getId().equals(canceller.getMemberId());
  }

  private boolean isCurrentUserAdminRole() {
    //    SecurityContext context = SecurityContextHolder.getContext();
    //    if (context == null) return false;
    //    Authentication authentication = context.getAuthentication();
    //    if (authentication == null) return false;
    //    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    //    if (authorities == null) return false;
    //    return authorities.stream().anyMatch(authority ->
    // authority.getAuthority().equals("ROLE_ADMIN"));
    return true;
  }
}
