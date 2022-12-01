package com.fern.nursery.api.client.token.endpoints;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public final class RevokeTokenById {
  private RevokeTokenById() {
  }

  public static final class Request {
    private final String tokenId;

    private int _cachedHashCode;

    Request(String tokenId) {
      this.tokenId = tokenId;
    }

    public String getTokenId() {
      return tokenId;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof Request && equalTo((Request) other);
    }

    private boolean equalTo(Request other) {
      return tokenId.equals(other.tokenId);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.tokenId);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "RevokeTokenById.Request{" + "tokenId: " + tokenId + "}";
    }

    public static TokenIdStage builder() {
      return new Builder();
    }

    public interface TokenIdStage {
      _FinalStage tokenId(String tokenId);

      Builder from(Request other);
    }

    public interface _FinalStage {
      Request build();
    }

    static final class Builder implements TokenIdStage, _FinalStage {
      private String tokenId;

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        tokenId(other.getTokenId());
        return this;
      }

      @Override
      public _FinalStage tokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
      }

      @Override
      public Request build() {
        return new Request(tokenId);
      }
    }
  }
}
