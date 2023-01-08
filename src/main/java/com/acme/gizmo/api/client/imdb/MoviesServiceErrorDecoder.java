package com.acme.gizmo.api.client.imdb;

import com.acme.gizmo.api.client.imdb.exceptions.CreateMovieException;
import com.acme.gizmo.api.client.imdb.exceptions.GetMovieException;
import com.acme.gizmo.api.core.ObjectMappers;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;

final class MoviesServiceErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      if (methodKey.contains("createMovie")) {
        return decodeException(response, CreateMovieException.class);
      }
      if (methodKey.contains("getMovie")) {
        return decodeException(response, GetMovieException.class);
      }
    }
    catch (IOException e) {
    }
    return new RuntimeException("Failed to read response body. Received status " + response.status() + " for method " + methodKey);
  }

  private static <T extends Exception> Exception decodeException(Response response, Class<T> clazz)
      throws IOException {
    return ObjectMappers.JSON_MAPPER.reader().withAttribute("statusCode", response.status()).readValue(response.body().asInputStream(), clazz);
  }
}
