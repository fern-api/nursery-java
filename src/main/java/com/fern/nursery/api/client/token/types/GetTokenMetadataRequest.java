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
    builder = GetTokenMetadataRequest.Builder.class
)
public final class GetTokenMetadataRequest {
  private final String token;

  private int _cachedHashCode;

  GetTokenMetadataRequest(String token) {
    this.token = token;
  }

  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof GetTokenMetadataRequest && equalTo((GetTokenMetadataRequest) other);
  }

  private boolean equalTo(GetTokenMetadataRequest other) {
    return token.equals(other.token);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.token);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "GetTokenMetadataRequest{" + "token: " + token + "}";
  }

  public static TokenStage builder() {
    return new Builder();
  }

  public interface TokenStage {
    _FinalStage token(String token);

    Builder from(GetTokenMetadataRequest other);
  }

  public interface _FinalStage {
    GetTokenMetadataRequest build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements TokenStage, _FinalStage {
    private String token;

    private Builder() {
    }

    @Override
    public Builder from(GetTokenMetadataRequest other) {
      token(other.getToken());
      return this;
    }

    @Override
    @JsonSetter("token")
    public _FinalStage token(String token) {
      this.token = token;
      return this;
    }

    @Override
    public GetTokenMetadataRequest build() {
      return new GetTokenMetadataRequest(token);
    }
  }
}
