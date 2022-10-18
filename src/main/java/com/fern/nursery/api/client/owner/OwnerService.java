package com.fern.nursery.api.client.owner;

import com.fern.java.jackson.ClientObjectMappers;
import com.fern.java.jersey.contracts.OptionalAwareContract;
import com.fern.nursery.api.client.owner.exceptions.CreateException;
import com.fern.nursery.api.client.owner.exceptions.GetException;
import com.fern.nursery.api.client.owner.exceptions.UpdateException;
import com.fern.nursery.api.client.owner.types.CreateOwnerRequest;
import com.fern.nursery.api.client.owner.types.Owner;
import com.fern.nursery.api.client.owner.types.OwnerId;
import com.fern.nursery.api.client.owner.types.UpdateOwnerRequest;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.lang.String;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/owner")
interface OwnerService {
  @POST
  @Path("/")
  void create(CreateOwnerRequest body) throws CreateException;

  @GET
  @Path("/{ownerId}")
  Owner get(@PathParam("ownerId") OwnerId ownerId) throws GetException;

  @PUT
  @Path("/{ownerId}")
  Owner update(@PathParam("ownerId") OwnerId ownerId, UpdateOwnerRequest body) throws
      UpdateException;

  static OwnerService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ClientObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ClientObjectMappers.JSON_MAPPER))
        .errorDecoder(new OwnerServiceErrorDecoder()).target(OwnerService.class, url);
  }
}
