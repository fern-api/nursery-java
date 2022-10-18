package com.fern.nursery.api.client.token;

import com.fern.java.jackson.ClientObjectMappers;
import com.fern.java.jersey.contracts.OptionalAwareContract;
import com.fern.nursery.api.client.owner.types.OwnerId;
import com.fern.nursery.api.client.token.exceptions.CreateException;
import com.fern.nursery.api.client.token.exceptions.GetTokenMetadataException;
import com.fern.nursery.api.client.token.exceptions.GetTokensForOwnerException;
import com.fern.nursery.api.client.token.types.CreateTokenRequest;
import com.fern.nursery.api.client.token.types.CreateTokenResponse;
import com.fern.nursery.api.client.token.types.GetTokenMetadataRequest;
import com.fern.nursery.api.client.token.types.TokenMetadata;
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
  List<TokenMetadata> getTokensForOwner(@PathParam("ownerId") OwnerId ownerId) throws
      GetTokensForOwnerException;

  static TokenService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ClientObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ClientObjectMappers.JSON_MAPPER))
        .errorDecoder(new TokenServiceErrorDecoder()).target(TokenService.class, url);
  }
}