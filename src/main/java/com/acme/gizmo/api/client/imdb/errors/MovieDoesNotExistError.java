package com.acme.gizmo.api.client.imdb.errors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public final class MovieDoesNotExistError {
  private final String value;

  private MovieDoesNotExistError(String value) {
    this.value = value;
  }

  @JsonValue
  public String get() {
    return this.value;
  }

  @Override
  public boolean equals(Object other) {
    return this == other || (other instanceof MovieDoesNotExistError && this.value.equals(((MovieDoesNotExistError) other).value));
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  public static MovieDoesNotExistError of(String value) {
    return new MovieDoesNotExistError(value);
  }
}
