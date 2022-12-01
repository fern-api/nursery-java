package com.fern.nursery;

import com.fern.nursery.client.owner.OwnerServiceClient;
import com.fern.nursery.client.token.TokenServiceClient;
import java.lang.String;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class FernNurseryClient {
  private final Supplier<OwnerServiceClient> ownerServiceClient;

  private final Supplier<TokenServiceClient> tokenServiceClient;

  public FernNurseryClient(String url) {
    this.ownerServiceClient = memoize(() -> new OwnerServiceClient(url));
    this.tokenServiceClient = memoize(() -> new TokenServiceClient(url));
  }

  public final OwnerServiceClient owner() {
    return this.ownerServiceClient.get();
  }

  public final TokenServiceClient token() {
    return this.tokenServiceClient.get();
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
