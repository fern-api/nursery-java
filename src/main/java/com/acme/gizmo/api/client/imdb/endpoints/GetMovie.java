package com.acme.gizmo.api.client.imdb.endpoints;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public final class GetMovie {
  private GetMovie() {
  }

  public static final class Request {
    private final String movieId;

    private int _cachedHashCode;

    Request(String movieId) {
      this.movieId = movieId;
    }

    public String getMovieId() {
      return movieId;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof Request && equalTo((Request) other);
    }

    private boolean equalTo(Request other) {
      return movieId.equals(other.movieId);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.movieId);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "GetMovie.Request{" + "movieId: " + movieId + "}";
    }

    public static MovieIdStage builder() {
      return new Builder();
    }

    public interface MovieIdStage {
      _FinalStage movieId(String movieId);

      Builder from(Request other);
    }

    public interface _FinalStage {
      Request build();
    }

    static final class Builder implements MovieIdStage, _FinalStage {
      private String movieId;

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        movieId(other.getMovieId());
        return this;
      }

      @Override
      public _FinalStage movieId(String movieId) {
        this.movieId = movieId;
        return this;
      }

      @Override
      public Request build() {
        return new Request(movieId);
      }
    }
  }
}
