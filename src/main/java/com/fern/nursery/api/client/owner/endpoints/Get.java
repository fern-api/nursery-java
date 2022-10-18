package com.fern.nursery.api.client.owner.endpoints;

import com.fern.nursery.api.client.owner.types.OwnerId;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public final class Get {
  private Get() {
  }

  public static final class Request {
    private final OwnerId ownerId;

    private int _cachedHashCode;

    Request(OwnerId ownerId) {
      this.ownerId = ownerId;
    }

    public OwnerId getOwnerId() {
      return ownerId;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof Request && equalTo((Request) other);
    }

    private boolean equalTo(Request other) {
      return ownerId.equals(other.ownerId);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.ownerId);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "Get.Request{" + "ownerId: " + ownerId + "}";
    }

    public static OwnerIdStage builder() {
      return new Builder();
    }

    public interface OwnerIdStage {
      _FinalStage ownerId(OwnerId ownerId);

      Builder from(Request other);
    }

    public interface _FinalStage {
      Request build();
    }

    static final class Builder implements OwnerIdStage, _FinalStage {
      private OwnerId ownerId;

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        ownerId(other.getOwnerId());
        return this;
      }

      @Override
      public _FinalStage ownerId(OwnerId ownerId) {
        this.ownerId = ownerId;
        return this;
      }

      @Override
      public Request build() {
        return new Request(ownerId);
      }
    }
  }
}
