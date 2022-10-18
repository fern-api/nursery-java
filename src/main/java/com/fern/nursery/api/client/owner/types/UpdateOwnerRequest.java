package com.fern.nursery.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(
    builder = UpdateOwnerRequest.Builder.class
)
public final class UpdateOwnerRequest {
  private final Optional<Object> data;

  private int _cachedHashCode;

  UpdateOwnerRequest(Optional<Object> data) {
    this.data = data;
  }

  @JsonProperty("data")
  public Optional<Object> getData() {
    return data;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof UpdateOwnerRequest && equalTo((UpdateOwnerRequest) other);
  }

  private boolean equalTo(UpdateOwnerRequest other) {
    return data.equals(other.data);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.data);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "UpdateOwnerRequest{" + "data: " + data + "}";
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder {
    private Optional<Object> data = Optional.empty();

    private Builder() {
    }

    public Builder from(UpdateOwnerRequest other) {
      data(other.getData());
      return this;
    }

    @JsonSetter(
        value = "data",
        nulls = Nulls.SKIP
    )
    public Builder data(Optional<Object> data) {
      this.data = data;
      return this;
    }

    public Builder data(Object data) {
      this.data = Optional.of(data);
      return this;
    }

    public UpdateOwnerRequest build() {
      return new UpdateOwnerRequest(data);
    }
  }
}
