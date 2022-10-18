package com.fern.nursery.api.client.token;

import com.fern.java.jackson.ClientObjectMappers;
import com.fern.nursery.api.client.token.exceptions.CreateException;
import com.fern.nursery.api.client.token.exceptions.GetTokenMetadataException;
import com.fern.nursery.api.client.token.exceptions.GetTokensForOwnerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;

final class TokenServiceErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      if (methodKey.contains("create")) {
        return decodeException(response, CreateException.class);
      }
      if (methodKey.contains("getTokenMetadata")) {
        return decodeException(response, GetTokenMetadataException.class);
      }
      if (methodKey.contains("getTokensForOwner")) {
        return decodeException(response, GetTokensForOwnerException.class);
      }
    }
    catch (IOException e) {
    }
    return new RuntimeException("Failed to read response body. Received status " + response.status() + " for method " + methodKey);
  }

  private static <T extends Exception> Exception decodeException(Response response, Class<T> clazz)
      throws IOException {
    return ClientObjectMappers.JSON_MAPPER.reader().withAttribute("statusCode", response.status()).readValue(response.body().asInputStream(), clazz);
  }
}
