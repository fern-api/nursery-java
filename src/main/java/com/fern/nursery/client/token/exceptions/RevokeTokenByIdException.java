package com.fern.nursery.client.token.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fern.nursery.client.token.errors.TokenNotFoundError;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    using = RevokeTokenByIdException.Deserializer.class
)
public final class RevokeTokenByIdException extends Exception {
  private final Value value;

  private int statusCode;

  private RevokeTokenByIdException(Value value, int statusCode) {
    this.value = value;
    this.statusCode = statusCode;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public static RevokeTokenByIdException tokenNotFoundError(TokenNotFoundError value) {
    return new RevokeTokenByIdException(new TokenNotFoundErrorValue(value), 404);
  }

  public static RevokeTokenByIdException other(Object unknownValue, int statusCode) {
    return new RevokeTokenByIdException(new UnknownErrorValue(unknownValue), statusCode);
  }

  public boolean isTokenNotFoundError() {
    return value instanceof TokenNotFoundErrorValue;
  }

  public boolean isOther() {
    return value instanceof UnknownErrorValue;
  }

  public Optional<TokenNotFoundError> getTokenNotFoundError() {
    if (isTokenNotFoundError()) {
      return Optional.of(((TokenNotFoundErrorValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<Object> getOther() {
    if (isOther()) {
      return Optional.of(((UnknownErrorValue) value).unknownValue);
    }
    return Optional.empty();
  }

  public interface Visitor<T> {
    T visitTokenNotFoundError(TokenNotFoundError tokenNotFoundError);

    T visitOther(Object other);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "_error",
      visible = true,
      defaultImpl = UnknownErrorValue.class
  )
  @JsonSubTypes(@JsonSubTypes.Type(TokenNotFoundErrorValue.class))
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("TokenNotFoundError")
  private static final class TokenNotFoundErrorValue implements Value {
    private TokenNotFoundError value;

    private String errorInstanceId;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private TokenNotFoundErrorValue(@JsonProperty("value") TokenNotFoundError value,
        @JsonProperty("_errorInstanceId") String errorInstanceId) {
      this.value = value;
      this.errorInstanceId = errorInstanceId;
    }

    private TokenNotFoundErrorValue(TokenNotFoundError value) {
      this.value = value;
      this.errorInstanceId = UUID.randomUUID().toString();
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitTokenNotFoundError(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof TokenNotFoundErrorValue && equalTo((TokenNotFoundErrorValue) other);
    }

    private boolean equalTo(TokenNotFoundErrorValue other) {
      return value.equals(other.value) && errorInstanceId.equals(other.errorInstanceId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.errorInstanceId);
    }

    @Override
    public String toString() {
      return "RevokeTokenByIdException{" + "value: " + value + ", errorInstanceId: " + errorInstanceId + "}";
    }
  }

  private static final class UnknownErrorValue implements Value {
    private Object unknownValue;

    @JsonCreator(
        mode = JsonCreator.Mode.DELEGATING
    )
    UnknownErrorValue(@JsonProperty("unknownValue") Object unknownValue) {
      this.unknownValue = unknownValue;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitOther(unknownValue);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof UnknownErrorValue && equalTo((UnknownErrorValue) other);
    }

    private boolean equalTo(UnknownErrorValue other) {
      return unknownValue.equals(other.unknownValue);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.unknownValue);
    }

    @Override
    public String toString() {
      return "RevokeTokenByIdException{" + "unknownValue: " + unknownValue + "}";
    }
  }

  static final class Deserializer extends JsonDeserializer<RevokeTokenByIdException> {
    @Override
    public RevokeTokenByIdException deserialize(JsonParser p, DeserializationContext ctx) throws
        IOException {
      Value value = ctx.readValue(p, Value.class);
      int statusCode = (int) ctx.getAttribute("statusCode");
      return new RevokeTokenByIdException(value, statusCode);
    }
  }
}
