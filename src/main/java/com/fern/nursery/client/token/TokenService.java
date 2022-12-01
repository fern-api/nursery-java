package com.fern.nursery.client.token;

import com.fern.java.jersey.contracts.OptionalAwareContract;
import com.fern.nursery.client.token.exceptions.CreateException;
import com.fern.nursery.client.token.exceptions.GetTokenMetadataException;
import com.fern.nursery.client.token.exceptions.GetTokensForOwnerException;
import com.fern.nursery.client.token.exceptions.RevokeTokenByIdException;
import com.fern.nursery.client.token.exceptions.RevokeTokenException;
import com.fern.nursery.client.token.types.CreateTokenRequest;
import com.fern.nursery.client.token.types.CreateTokenResponse;
import com.fern.nursery.client.token.types.GetTokenMetadataRequest;
import com.fern.nursery.client.token.types.RevokeTokenRequest;
import com.fern.nursery.client.token.types.TokenMetadata;
import com.fern.nursery.core.ObjectMappers;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.lang.String;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tokens")
interface TokenService {
  @POST
  @Path("/create")
  CreateTokenResponse create(CreateTokenRequest body) throws CreateException;

  @POST
  @Path("/metadata")
  TokenMetadata getTokenMetadata(GetTokenMetadataRequest body) throws GetTokenMetadataException;

  @GET
  @Path("/owner/{ownerId}")
  List<TokenMetadata> getTokensForOwner(@PathParam("ownerId") String ownerId) throws
      GetTokensForOwnerException;

  @POST
  @Path("/revoke/{tokenId}")
  void revokeTokenById(@PathParam("tokenId") String tokenId) throws RevokeTokenByIdException;

  @POST
  @Path("/revoke")
  void revokeToken(RevokeTokenRequest body) throws RevokeTokenException;

  static TokenService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ObjectMappers.JSON_MAPPER))
        .errorDecoder(new TokenServiceErrorDecoder()).target(TokenService.class, url);
  }
}
