package com.juny.dddstart2.order.command.domain;

import com.juny.dddstart2.member.command.domain.MemberId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Orderer {
  @AttributeOverrides(
      @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
  )
  private MemberId memberId;

  @Column(name = "orderer_name")
  private String name;

  protected Orderer() {
  }

  public Orderer(MemberId memberId, String name) {
    this.memberId = memberId;
    this.name = name;
  }

  public MemberId getMemberId() {
    return memberId;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Orderer orderer = (Orderer) o;
    return Objects.equals(memberId, orderer.memberId) &&
        Objects.equals(name, orderer.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId, name);
  }
}
