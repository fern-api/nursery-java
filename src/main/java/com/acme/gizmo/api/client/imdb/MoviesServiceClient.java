package com.acme.gizmo.api.client.imdb;

import com.acme.gizmo.api.client.imdb.endpoints.CreateMovie;
import com.acme.gizmo.api.client.imdb.endpoints.GetMovie;
import com.acme.gizmo.api.client.imdb.exceptions.CreateMovieException;
import com.acme.gizmo.api.client.imdb.exceptions.GetMovieException;
import com.acme.gizmo.api.client.imdb.types.Movie;
import java.lang.String;

public final class MoviesServiceClient {
  private final MoviesService service;

  public MoviesServiceClient(String url) {
    this.service = MoviesService.getClient(url);
  }

  public String createMovie(CreateMovie.Request request) throws CreateMovieException {
    return this.service.createMovie(request.getBody());
  }

  public Movie getMovie(GetMovie.Request request) throws GetMovieException {
    return this.service.getMovie(request.getMovieId());
  }
}
