package com.acme.gizmo.api.client.imdb.endpoints;

import com.acme.gizmo.api.client.imdb.types.CreateMovieRequest;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public final class CreateMovie {
  private CreateMovie() {
  }

  public static final class Request {
    private final CreateMovieRequest body;

    private int _cachedHashCode;

    Request(CreateMovieRequest body) {
      this.body = body;
    }

    public CreateMovieRequest getBody() {
      return body;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof Request && equalTo((Request) other);
    }

    private boolean equalTo(Request other) {
      return body.equals(other.body);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.body);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "CreateMovie.Request{" + "body: " + body + "}";
    }

    public static BodyStage builder() {
      return new Builder();
    }

    public interface BodyStage {
      _FinalStage body(CreateMovieRequest body);

      Builder from(Request other);
    }

    public interface _FinalStage {
      Request build();
    }

    static final class Builder implements BodyStage, _FinalStage {
      private CreateMovieRequest body;

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        body(other.getBody());
        return this;
      }

      @Override
      public _FinalStage body(CreateMovieRequest body) {
        this.body = body;
        return this;
      }

      @Override
      public Request build() {
        return new Request(body);
      }
    }
  }
}
