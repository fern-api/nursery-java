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
    builder = CreateOwnerRequest.Builder.class
)
public final class CreateOwnerRequest {
  private final OwnerId ownerId;

  private final Object data;

  private int _cachedHashCode;

  CreateOwnerRequest(OwnerId ownerId, Object data) {
    this.ownerId = ownerId;
    this.data = data;
  }

  @JsonProperty("ownerId")
  public OwnerId getOwnerId() {
    return ownerId;
  }

  @JsonProperty("data")
  public Object getData() {
    return data;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof CreateOwnerRequest && equalTo((CreateOwnerRequest) other);
  }

  private boolean equalTo(CreateOwnerRequest other) {
    return ownerId.equals(other.ownerId) && data.equals(other.data);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.ownerId, this.data);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "CreateOwnerRequest{" + "ownerId: " + ownerId + ", data: " + data + "}";
  }

  public static OwnerIdStage builder() {
    return new Builder();
  }

  public interface OwnerIdStage {
    DataStage ownerId(OwnerId ownerId);

    Builder from(CreateOwnerRequest other);
  }

  public interface DataStage {
    _FinalStage data(Object data);
  }

  public interface _FinalStage {
    CreateOwnerRequest build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements OwnerIdStage, DataStage, _FinalStage {
    private OwnerId ownerId;

    private Object data;

    private Builder() {
    }

    @Override
    public Builder from(CreateOwnerRequest other) {
      ownerId(other.getOwnerId());
      data(other.getData());
      return this;
    }

    @Override
    @JsonSetter("ownerId")
    public DataStage ownerId(OwnerId ownerId) {
      this.ownerId = ownerId;
      return this;
    }

    @Override
    @JsonSetter("data")
    public _FinalStage data(Object data) {
      this.data = data;
      return this;
    }

    @Override
    public CreateOwnerRequest build() {
      return new CreateOwnerRequest(ownerId, data);
    }
  }
}
