package com.fern.nursery.api.client.token.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

@JsonDeserialize(
    builder = CreateTokenResponse.Builder.class
)
public final class CreateTokenResponse {
  private final String token;

  private final TokenId tokenId;

  private int _cachedHashCode;

  CreateTokenResponse(String token, TokenId tokenId) {
    this.token = token;
    this.tokenId = tokenId;
  }

  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  @JsonProperty("tokenId")
  public TokenId getTokenId() {
    return tokenId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof CreateTokenResponse && equalTo((CreateTokenResponse) other);
  }

  private boolean equalTo(CreateTokenResponse other) {
    return token.equals(other.token) && tokenId.equals(other.tokenId);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.token, this.tokenId);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "CreateTokenResponse{" + "token: " + token + ", tokenId: " + tokenId + "}";
  }

  public static TokenStage builder() {
    return new Builder();
  }

  public interface TokenStage {
    TokenIdStage token(String token);

    Builder from(CreateTokenResponse other);
  }

  public interface TokenIdStage {
    _FinalStage tokenId(TokenId tokenId);
  }

  public interface _FinalStage {
    CreateTokenResponse build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements TokenStage, TokenIdStage, _FinalStage {
    private String token;

    private TokenId tokenId;

    private Builder() {
    }

    @Override
    public Builder from(CreateTokenResponse other) {
      token(other.getToken());
      tokenId(other.getTokenId());
      return this;
    }

    @Override
    @JsonSetter("token")
    public TokenIdStage token(String token) {
      this.token = token;
      return this;
    }

    @Override
    @JsonSetter("tokenId")
    public _FinalStage tokenId(TokenId tokenId) {
      this.tokenId = tokenId;
      return this;
    }

    @Override
    public CreateTokenResponse build() {
      return new CreateTokenResponse(token, tokenId);
    }
  }
}
