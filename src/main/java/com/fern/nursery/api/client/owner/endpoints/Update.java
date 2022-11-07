package com.fern.nursery.api.client.owner.endpoints;

import com.fern.nursery.api.client.owner.types.UpdateOwnerRequest;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public final class Update {
  private Update() {
  }

  public static final class Request {
    private final String ownerId;

    private final UpdateOwnerRequest body;

    private int _cachedHashCode;

    Request(String ownerId, UpdateOwnerRequest body) {
      this.ownerId = ownerId;
      this.body = body;
    }

    public String getOwnerId() {
      return ownerId;
    }

    public UpdateOwnerRequest getBody() {
      return body;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof Request && equalTo((Request) other);
    }

    private boolean equalTo(Request other) {
      return ownerId.equals(other.ownerId) && body.equals(other.body);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.ownerId, this.body);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "Update.Request{" + "ownerId: " + ownerId + ", body: " + body + "}";
    }

    public static OwnerIdStage builder() {
      return new Builder();
    }

    public interface OwnerIdStage {
      BodyStage ownerId(String ownerId);

      Builder from(Request other);
    }

    public interface BodyStage {
      _FinalStage body(UpdateOwnerRequest body);
    }

    public interface _FinalStage {
      Request build();
    }

    static final class Builder implements OwnerIdStage, BodyStage, _FinalStage {
      private String ownerId;

      private UpdateOwnerRequest body;

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        ownerId(other.getOwnerId());
        body(other.getBody());
        return this;
      }

      @Override
      public BodyStage ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
      }

      @Override
      public _FinalStage body(UpdateOwnerRequest body) {
        this.body = body;
        return this;
      }

      @Override
      public Request build() {
        return new Request(ownerId, body);
      }
    }
  }
}
