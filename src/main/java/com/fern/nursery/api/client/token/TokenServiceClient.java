package com.fern.nursery.api.client.token;

import com.fern.nursery.api.client.token.endpoints.Create;
import com.fern.nursery.api.client.token.endpoints.GetTokenMetadata;
import com.fern.nursery.api.client.token.endpoints.GetTokensForOwner;
import com.fern.nursery.api.client.token.exceptions.CreateException;
import com.fern.nursery.api.client.token.exceptions.GetTokenMetadataException;
import com.fern.nursery.api.client.token.exceptions.GetTokensForOwnerException;
import com.fern.nursery.api.client.token.types.CreateTokenResponse;
import com.fern.nursery.api.client.token.types.TokenMetadata;
import java.lang.String;
import java.util.List;

public final class TokenServiceClient {
  private final TokenService service;

  public TokenServiceClient(String url) {
    this.service = TokenService.getClient(url);
  }

  public CreateTokenResponse create(Create.Request request) throws CreateException {
    return this.service.create(request.getBody());
  }

  public TokenMetadata getTokenMetadata(GetTokenMetadata.Request request) throws
      GetTokenMetadataException {
    return this.service.getTokenMetadata(request.getBody());
  }

  public List<TokenMetadata> getTokensForOwner(GetTokensForOwner.Request request) throws
      GetTokensForOwnerException {
    return this.service.getTokensForOwner(request.getOwnerId());
  }
}
