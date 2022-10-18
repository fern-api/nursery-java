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
    builder = Owner.Builder.class
)
public final class Owner {
  private final OwnerId ownerId;

  private final Optional<Object> data;

  private int _cachedHashCode;

  Owner(OwnerId ownerId, Optional<Object> data) {
    this.ownerId = ownerId;
    this.data = data;
  }

  @JsonProperty("ownerId")
  public OwnerId getOwnerId() {
    return ownerId;
  }

  @JsonProperty("data")
  public Optional<Object> getData() {
    return data;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof Owner && equalTo((Owner) other);
  }

  private boolean equalTo(Owner other) {
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
    return "Owner{" + "ownerId: " + ownerId + ", data: " + data + "}";
  }

  public static OwnerIdStage builder() {
    return new Builder();
  }

  public interface OwnerIdStage {
    _FinalStage ownerId(OwnerId ownerId);

    Builder from(Owner other);
  }

  public interface _FinalStage {
    Owner build();

    _FinalStage data(Optional<Object> data);

    _FinalStage data(Object data);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements OwnerIdStage, _FinalStage {
    private OwnerId ownerId;

    private Optional<Object> data = Optional.empty();

    private Builder() {
    }

    @Override
    public Builder from(Owner other) {
      ownerId(other.getOwnerId());
      data(other.getData());
      return this;
    }

    @Override
    @JsonSetter("ownerId")
    public _FinalStage ownerId(OwnerId ownerId) {
      this.ownerId = ownerId;
      return this;
    }

    @Override
    public _FinalStage data(Object data) {
      this.data = Optional.of(data);
      return this;
    }

    @Override
    @JsonSetter(
        value = "data",
        nulls = Nulls.SKIP
    )
    public _FinalStage data(Optional<Object> data) {
      this.data = data;
      return this;
    }

    @Override
    public Owner build() {
      return new Owner(ownerId, data);
    }
  }
}
