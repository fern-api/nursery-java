package com.fern.nursery.api.client.token.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public final class TokenStatus {
  private final Value value;

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  private TokenStatus(Value value) {
    this.value = value;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public static TokenStatus active() {
    return new TokenStatus(new ActiveValue());
  }

  public static TokenStatus expired() {
    return new TokenStatus(new ExpiredValue());
  }

  public static TokenStatus revoked() {
    return new TokenStatus(new RevokedValue());
  }

  public boolean isActive() {
    return value instanceof ActiveValue;
  }

  public boolean isExpired() {
    return value instanceof ExpiredValue;
  }

  public boolean isRevoked() {
    return value instanceof RevokedValue;
  }

  public boolean _isUnknown() {
    return value instanceof _UnknownValue;
  }

  public Optional<Object> _getUnknown() {
    if (_isUnknown()) {
      return Optional.of(((_UnknownValue) value).value);
    }
    return Optional.empty();
  }

  @JsonValue
  private Value getValue() {
    return this.value;
  }

  public interface Visitor<T> {
    T visitActive();

    T visitExpired();

    T visitRevoked();

    T _visitUnknown(Object unknown);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "type",
      visible = true,
      defaultImpl = _UnknownValue.class
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(ActiveValue.class),
      @JsonSubTypes.Type(ExpiredValue.class),
      @JsonSubTypes.Type(RevokedValue.class)
  })
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("active")
  private static final class ActiveValue implements Value {
    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private ActiveValue() {
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitActive();
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof ActiveValue;
    }

    @Override
    public String toString() {
      return "TokenStatus{" + "}";
    }
  }

  @JsonTypeName("expired")
  private static final class ExpiredValue implements Value {
    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private ExpiredValue() {
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitExpired();
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof ExpiredValue;
    }

    @Override
    public String toString() {
      return "TokenStatus{" + "}";
    }
  }

  @JsonTypeName("revoked")
  private static final class RevokedValue implements Value {
    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private RevokedValue() {
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitRevoked();
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof RevokedValue;
    }

    @Override
    public String toString() {
      return "TokenStatus{" + "}";
    }
  }

  private static final class _UnknownValue implements Value {
    private String type;

    @JsonValue
    private Object value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private _UnknownValue(@JsonProperty("value") Object value) {
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor._visitUnknown(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof _UnknownValue && equalTo((_UnknownValue) other);
    }

    private boolean equalTo(_UnknownValue other) {
      return type.equals(other.type) && value.equals(other.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.type, this.value);
    }

    @Override
    public String toString() {
      return "TokenStatus{" + "type: " + type + ", value: " + value + "}";
    }
  }
}
