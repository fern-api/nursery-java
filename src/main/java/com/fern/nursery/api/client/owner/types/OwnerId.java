package com.fern.nursery.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public final class OwnerId {
  private final String value;

  private OwnerId(String value) {
    this.value = value;
  }

  @JsonValue
  public String get() {
    return this.value;
  }

  @Override
  public boolean equals(Object other) {
    return this == other || (other instanceof OwnerId && this.value.equals(((OwnerId) other).value));
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value;
  }

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  public static OwnerId of(String value) {
    return new OwnerId(value);
  }

  public static OwnerId valueOf(String value) {
    return of(value);
  }
}
