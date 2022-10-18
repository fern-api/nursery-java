package com.fern.nursery.api.client.token.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fern.nursery.api.client.owner.types.OwnerId;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(
    builder = TokenMetadata.Builder.class
)
public final class TokenMetadata {
  private final TokenId tokenId;

  private final OwnerId ownerId;

  private final Optional<String> description;

  private final String createdTime;

  private final TokenStatus status;

  private int _cachedHashCode;

  TokenMetadata(TokenId tokenId, OwnerId ownerId, Optional<String> description, String createdTime,
      TokenStatus status) {
    this.tokenId = tokenId;
    this.ownerId = ownerId;
    this.description = description;
    this.createdTime = createdTime;
    this.status = status;
  }

  @JsonProperty("tokenId")
  public TokenId getTokenId() {
    return tokenId;
  }

  @JsonProperty("ownerId")
  public OwnerId getOwnerId() {
    return ownerId;
  }

  @JsonProperty("description")
  public Optional<String> getDescription() {
    return description;
  }

  @JsonProperty("createdTime")
  public String getCreatedTime() {
    return createdTime;
  }

  @JsonProperty("status")
  public TokenStatus getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof TokenMetadata && equalTo((TokenMetadata) other);
  }

  private boolean equalTo(TokenMetadata other) {
    return tokenId.equals(other.tokenId) && ownerId.equals(other.ownerId) && description.equals(other.description) && createdTime.equals(other.createdTime) && status.equals(other.status);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.tokenId, this.ownerId, this.description, this.createdTime, this.status);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "TokenMetadata{" + "tokenId: " + tokenId + ", ownerId: " + ownerId + ", description: " + description + ", createdTime: " + createdTime + ", status: " + status + "}";
  }

  public static TokenIdStage builder() {
    return new Builder();
  }

  public interface TokenIdStage {
    OwnerIdStage tokenId(TokenId tokenId);

    Builder from(TokenMetadata other);
  }

  public interface OwnerIdStage {
    CreatedTimeStage ownerId(OwnerId ownerId);
  }

  public interface CreatedTimeStage {
    StatusStage createdTime(String createdTime);
  }

  public interface StatusStage {
    _FinalStage status(TokenStatus status);
  }

  public interface _FinalStage {
    TokenMetadata build();

    _FinalStage description(Optional<String> description);

    _FinalStage description(String description);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements TokenIdStage, OwnerIdStage, CreatedTimeStage, StatusStage, _FinalStage {
    private TokenId tokenId;

    private OwnerId ownerId;

    private String createdTime;

    private TokenStatus status;

    private Optional<String> description = Optional.empty();

    private Builder() {
    }

    @Override
    public Builder from(TokenMetadata other) {
      tokenId(other.getTokenId());
      ownerId(other.getOwnerId());
      description(other.getDescription());
      createdTime(other.getCreatedTime());
      status(other.getStatus());
      return this;
    }

    @Override
    @JsonSetter("tokenId")
    public OwnerIdStage tokenId(TokenId tokenId) {
      this.tokenId = tokenId;
      return this;
    }

    @Override
    @JsonSetter("ownerId")
    public CreatedTimeStage ownerId(OwnerId ownerId) {
      this.ownerId = ownerId;
      return this;
    }

    @Override
    @JsonSetter("createdTime")
    public StatusStage createdTime(String createdTime) {
      this.createdTime = createdTime;
      return this;
    }

    @Override
    @JsonSetter("status")
    public _FinalStage status(TokenStatus status) {
      this.status = status;
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
    public TokenMetadata build() {
      return new TokenMetadata(tokenId, ownerId, description, createdTime, status);
    }
  }
}
