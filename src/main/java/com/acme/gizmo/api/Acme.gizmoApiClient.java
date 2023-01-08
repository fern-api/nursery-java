package com.acme.gizmo.api;

import com.acme.gizmo.api.client.imdb.MoviesServiceClient;
import java.lang.String;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class Acme.gizmoApiClient {
  private final Supplier<MoviesServiceClient> moviesServiceClient;

  public Acme.gizmoApiClient(String url) {
    this.moviesServiceClient = memoize(() -> new MoviesServiceClient(url));
  }

  public final MoviesServiceClient imdb() {
    return this.moviesServiceClient.get();
  }

  private static <T> Supplier<T> memoize(Supplier<T> delegate) {
    AtomicReference<T> value = new AtomicReference<>();
    return () ->  {
      T val = value.get();
      if (val == null) {
        val = value.updateAndGet(cur -> cur == null ? Objects.requireNonNull(delegate.get()) : cur);
      }
      return val;
    } ;
  }
}
