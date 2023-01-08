package com.fern.nursery.client.owner.exceptions;

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
import com.fern.nursery.client.owner.errors.OwnerAlreadyExistsError;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    using = CreateException.Deserializer.class
)
public final class CreateException extends Exception {
  private final Value value;

  private int statusCode;

  private CreateException(Value value, int statusCode) {
    this.value = value;
    this.statusCode = statusCode;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public static CreateException ownerAlreadyExistsError(OwnerAlreadyExistsError value) {
    return new CreateException(new OwnerAlreadyExistsErrorValue(value), 400);
  }

  public static CreateException other(Object unknownValue, int statusCode) {
    return new CreateException(new UnknownErrorValue(unknownValue), statusCode);
  }

  public boolean isOwnerAlreadyExistsError() {
    return value instanceof OwnerAlreadyExistsErrorValue;
  }

  public boolean isOther() {
    return value instanceof UnknownErrorValue;
  }

  public Optional<OwnerAlreadyExistsError> getOwnerAlreadyExistsError() {
    if (isOwnerAlreadyExistsError()) {
      return Optional.of(((OwnerAlreadyExistsErrorValue) value).value);
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
    T visitOwnerAlreadyExistsError(OwnerAlreadyExistsError ownerAlreadyExistsError);

    T visitOther(Object other);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "_error",
      visible = true,
      defaultImpl = UnknownErrorValue.class
  )
  @JsonSubTypes(@JsonSubTypes.Type(OwnerAlreadyExistsErrorValue.class))
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("OwnerAlreadyExistsError")
  private static final class OwnerAlreadyExistsErrorValue implements Value {
    private OwnerAlreadyExistsError value;

    private String errorInstanceId;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private OwnerAlreadyExistsErrorValue(@JsonProperty("value") OwnerAlreadyExistsError value,
        @JsonProperty("_errorInstanceId") String errorInstanceId) {
      this.value = value;
      this.errorInstanceId = errorInstanceId;
    }

    private OwnerAlreadyExistsErrorValue(OwnerAlreadyExistsError value) {
      this.value = value;
      this.errorInstanceId = UUID.randomUUID().toString();
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitOwnerAlreadyExistsError(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof OwnerAlreadyExistsErrorValue && equalTo((OwnerAlreadyExistsErrorValue) other);
    }

    private boolean equalTo(OwnerAlreadyExistsErrorValue other) {
      return value.equals(other.value) && errorInstanceId.equals(other.errorInstanceId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.errorInstanceId);
    }

    @Override
    public String toString() {
      return "CreateException{" + "value: " + value + ", errorInstanceId: " + errorInstanceId + "}";
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
      return "CreateException{" + "unknownValue: " + unknownValue + "}";
    }
  }

  static final class Deserializer extends JsonDeserializer<CreateException> {
    @Override
    public CreateException deserialize(JsonParser p, DeserializationContext ctx) throws
        IOException {
      Value value = ctx.readValue(p, Value.class);
      int statusCode = (int) ctx.getAttribute("statusCode");
      return new CreateException(value, statusCode);
    }
  }
}
