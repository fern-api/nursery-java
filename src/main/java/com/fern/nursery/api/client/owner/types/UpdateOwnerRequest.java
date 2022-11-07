package com.fern.nursery.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

@JsonDeserialize(
    builder = UpdateOwnerRequest.Builder.class
)
public final class UpdateOwnerRequest {
  private final Object data;

  private int _cachedHashCode;

  UpdateOwnerRequest(Object data) {
    this.data = data;
  }

  @JsonProperty("data")
  public Object getData() {
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

  public static DataStage builder() {
    return new Builder();
  }

  public interface DataStage {
    _FinalStage data(Object data);

    Builder from(UpdateOwnerRequest other);
  }

  public interface _FinalStage {
    UpdateOwnerRequest build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements DataStage, _FinalStage {
    private Object data;

    private Builder() {
    }

    @Override
    public Builder from(UpdateOwnerRequest other) {
      data(other.getData());
      return this;
    }

    @Override
    @JsonSetter("data")
    public _FinalStage data(Object data) {
      this.data = data;
      return this;
    }

    @Override
    public UpdateOwnerRequest build() {
      return new UpdateOwnerRequest(data);
    }
  }
}
