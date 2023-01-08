package com.acme.gizmo.api.client.imdb.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

@JsonDeserialize(
    builder = Movie.Builder.class
)
public final class Movie {
  private final String id;

  private final String title;

  private final double rating;

  private int _cachedHashCode;

  Movie(String id, String title, double rating) {
    this.id = id;
    this.title = title;
    this.rating = rating;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  @JsonProperty("rating")
  public double getRating() {
    return rating;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof Movie && equalTo((Movie) other);
  }

  private boolean equalTo(Movie other) {
    return id.equals(other.id) && title.equals(other.title) && rating == other.rating;
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.id, this.title, this.rating);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "Movie{" + "id: " + id + ", title: " + title + ", rating: " + rating + "}";
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    TitleStage id(String id);

    Builder from(Movie other);
  }

  public interface TitleStage {
    RatingStage title(String title);
  }

  public interface RatingStage {
    _FinalStage rating(double rating);
  }

  public interface _FinalStage {
    Movie build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  static final class Builder implements IdStage, TitleStage, RatingStage, _FinalStage {
    private String id;

    private String title;

    private double rating;

    private Builder() {
    }

    @Override
    public Builder from(Movie other) {
      id(other.getId());
      title(other.getTitle());
      rating(other.getRating());
      return this;
    }

    @Override
    @JsonSetter("id")
    public TitleStage id(String id) {
      this.id = id;
      return this;
    }

    @Override
    @JsonSetter("title")
    public RatingStage title(String title) {
      this.title = title;
      return this;
    }

    @Override
    @JsonSetter("rating")
    public _FinalStage rating(double rating) {
      this.rating = rating;
      return this;
    }

    @Override
    public Movie build() {
      return new Movie(id, title, rating);
    }
  }
}
