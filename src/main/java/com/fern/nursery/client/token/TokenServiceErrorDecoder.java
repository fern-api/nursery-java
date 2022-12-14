package com.fern.nursery.client.token;

import com.fern.nursery.client.token.exceptions.CreateException;
import com.fern.nursery.client.token.exceptions.GetTokenMetadataException;
import com.fern.nursery.client.token.exceptions.GetTokensForOwnerException;
import com.fern.nursery.client.token.exceptions.RevokeTokenByIdException;
import com.fern.nursery.client.token.exceptions.RevokeTokenException;
import com.fern.nursery.core.ObjectMappers;
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
      if (methodKey.contains("revokeTokenById")) {
        return decodeException(response, RevokeTokenByIdException.class);
      }
      if (methodKey.contains("revokeToken")) {
        return decodeException(response, RevokeTokenException.class);
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
