package com.fern.nursery.client.token.errors;

public final class TokenNotFoundError {
  private TokenNotFoundError() {
  }

  public static TokenNotFoundError of() {
    return new TokenNotFoundError();
  }
}
