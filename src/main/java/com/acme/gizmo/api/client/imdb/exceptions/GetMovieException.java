package com.acme.gizmo.api.client.imdb.exceptions;

import com.acme.gizmo.api.client.imdb.errors.MovieDoesNotExistError;
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
import java.io.IOException;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    using = GetMovieException.Deserializer.class
)
public final class GetMovieException extends Exception {
  private final Value value;

  private int statusCode;

  private GetMovieException(Value value, int statusCode) {
    this.value = value;
    this.statusCode = statusCode;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public static GetMovieException movieDoesNotExistError(MovieDoesNotExistError value) {
    return new GetMovieException(new MovieDoesNotExistErrorValue(value), 404);
  }

  public static GetMovieException other(Object unknownValue, int statusCode) {
    return new GetMovieException(new UnknownErrorValue(unknownValue), statusCode);
  }

  public boolean isMovieDoesNotExistError() {
    return value instanceof MovieDoesNotExistErrorValue;
  }

  public boolean isOther() {
    return value instanceof UnknownErrorValue;
  }

  public Optional<MovieDoesNotExistError> getMovieDoesNotExistError() {
    if (isMovieDoesNotExistError()) {
      return Optional.of(((MovieDoesNotExistErrorValue) value).value);
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
    T visitMovieDoesNotExistError(MovieDoesNotExistError movieDoesNotExistError);

    T visitOther(Object other);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "_error",
      visible = true,
      defaultImpl = UnknownErrorValue.class
  )
  @JsonSubTypes(@JsonSubTypes.Type(MovieDoesNotExistErrorValue.class))
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("MovieDoesNotExistError")
  private static final class MovieDoesNotExistErrorValue implements Value {
    private MovieDoesNotExistError value;

    private String errorInstanceId;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private MovieDoesNotExistErrorValue(@JsonProperty("value") MovieDoesNotExistError value,
        @JsonProperty("_errorInstanceId") String errorInstanceId) {
      this.value = value;
      this.errorInstanceId = errorInstanceId;
    }

    private MovieDoesNotExistErrorValue(MovieDoesNotExistError value) {
      this.value = value;
      this.errorInstanceId = UUID.randomUUID().toString();
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitMovieDoesNotExistError(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof MovieDoesNotExistErrorValue && equalTo((MovieDoesNotExistErrorValue) other);
    }

    private boolean equalTo(MovieDoesNotExistErrorValue other) {
      return value.equals(other.value) && errorInstanceId.equals(other.errorInstanceId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.errorInstanceId);
    }

    @Override
    public String toString() {
      return "GetMovieException{" + "value: " + value + ", errorInstanceId: " + errorInstanceId + "}";
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
      return "GetMovieException{" + "unknownValue: " + unknownValue + "}";
    }
  }

  static final class Deserializer extends JsonDeserializer<GetMovieException> {
    @Override
    public GetMovieException deserialize(JsonParser p, DeserializationContext ctx) throws
        IOException {
      Value value = ctx.readValue(p, Value.class);
      int statusCode = (int) ctx.getAttribute("statusCode");
      return new GetMovieException(value, statusCode);
    }
  }
}
