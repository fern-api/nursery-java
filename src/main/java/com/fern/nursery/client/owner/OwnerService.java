package com.fern.nursery.client.owner;

import com.fern.java.jersey.contracts.OptionalAwareContract;
import com.fern.nursery.client.owner.exceptions.CreateException;
import com.fern.nursery.client.owner.exceptions.GetException;
import com.fern.nursery.client.owner.exceptions.UpdateException;
import com.fern.nursery.client.owner.types.CreateOwnerRequest;
import com.fern.nursery.client.owner.types.Owner;
import com.fern.nursery.client.owner.types.UpdateOwnerRequest;
import com.fern.nursery.core.ObjectMappers;
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
  @Path("")
  void create(CreateOwnerRequest body) throws CreateException;

  @GET
  @Path("/{ownerId}")
  Owner get(@PathParam("ownerId") String ownerId) throws GetException;

  @PUT
  @Path("/{ownerId}")
  Owner update(@PathParam("ownerId") String ownerId, UpdateOwnerRequest body) throws
      UpdateException;

  static OwnerService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ObjectMappers.JSON_MAPPER))
        .errorDecoder(new OwnerServiceErrorDecoder()).target(OwnerService.class, url);
  }
}
