package com.fern.nursery.client.token;

import com.fern.nursery.client.token.endpoints.Create;
import com.fern.nursery.client.token.endpoints.GetTokenMetadata;
import com.fern.nursery.client.token.endpoints.GetTokensForOwner;
import com.fern.nursery.client.token.endpoints.RevokeToken;
import com.fern.nursery.client.token.endpoints.RevokeTokenById;
import com.fern.nursery.client.token.exceptions.CreateException;
import com.fern.nursery.client.token.exceptions.GetTokenMetadataException;
import com.fern.nursery.client.token.exceptions.GetTokensForOwnerException;
import com.fern.nursery.client.token.exceptions.RevokeTokenByIdException;
import com.fern.nursery.client.token.exceptions.RevokeTokenException;
import com.fern.nursery.client.token.types.CreateTokenResponse;
import com.fern.nursery.client.token.types.TokenMetadata;
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

  public void revokeTokenById(RevokeTokenById.Request request) throws RevokeTokenByIdException {
    this.service.revokeTokenById(request.getTokenId());
  }

  public void revokeToken(RevokeToken.Request request) throws RevokeTokenException {
    this.service.revokeToken(request.getBody());
  }
}
