package com.fern.nursery.client.token.types;

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
    builder = CreateTokenRequest.Builder.class
)
public final class CreateTokenRequest {
  private final String ownerId;

  private final Optional<String> prefix;

  private final Optional<String> description;

  private int _cachedHashCode;

  CreateTokenRequest(String ownerId, Optional<String> prefix, Optional<String> description) {
    this.ownerId = ownerId;
    this.prefix = prefix;
    this.description = description;
  }

  @JsonProperty("ownerId")
  public String getOwnerId() {
    return ownerId;
  }

  @JsonProperty("prefix")
  public Optional<String> getPrefix() {
    return prefix;
  }

  @JsonProperty("description")
  public Optional<String> getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof CreateTokenRequest && equalTo((CreateTokenRequest) other);
  }

  private boolean equalTo(CreateTokenRequest other) {
    return ownerId.equals(other.ownerId) && prefix.equals(other.prefix) && description.equals(other.description);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.ownerId, this.prefix, this.description);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "CreateTokenRequest{" + "ownerId: " + ownerId + ", prefix: " + prefix + ", description: " + description + "}";
  }

  public static OwnerIdStage builder() {
    return new Builder();
  }

  public interface OwnerIdStage {
    _FinalStage ownerId(String ownerId);

    Builder from(CreateTokenRequest other);
  }

  public interface _FinalStage {
    CreateTokenRequest build();

    _FinalStage prefix(Optional<String> prefix);

    _FinalStage prefix(String prefix);

    _FinalStage description(Optional<String> description);

    _FinalStage description(String description);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements OwnerIdStage, _FinalStage {
    private String ownerId;

    private Optional<String> description = Optional.empty();

    private Optional<String> prefix = Optional.empty();

    private Builder() {
    }

    @Override
    public Builder from(CreateTokenRequest other) {
      ownerId(other.getOwnerId());
      prefix(other.getPrefix());
      description(other.getDescription());
      return this;
    }

    @Override
    @JsonSetter("ownerId")
    public _FinalStage ownerId(String ownerId) {
      this.ownerId = ownerId;
      return this;
    }

    @Override
    public _FinalStage description(String description) {
      this.description = Optional.of(description);
      return this;
    }

    @Override
    @JsonSetter(
        value = "description",
        nulls = Nulls.SKIP
    )
    public _FinalStage description(Optional<String> description) {
      this.description = description;
      return this;
    }

    @Override
    public _FinalStage prefix(String prefix) {
      this.prefix = Optional.of(prefix);
      return this;
    }

    @Override
    @JsonSetter(
        value = "prefix",
        nulls = Nulls.SKIP
    )
    public _FinalStage prefix(Optional<String> prefix) {
      this.prefix = prefix;
      return this;
    }

    @Override
    public CreateTokenRequest build() {
      return new CreateTokenRequest(ownerId, prefix, description);
    }
  }
}
