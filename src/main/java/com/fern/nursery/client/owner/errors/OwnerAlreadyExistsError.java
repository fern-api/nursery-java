package com.fern.nursery.client.owner.errors;

public final class OwnerAlreadyExistsError {
  private OwnerAlreadyExistsError() {
  }

  public static OwnerAlreadyExistsError of() {
    return new OwnerAlreadyExistsError();
  }
}
