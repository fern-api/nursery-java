package com.acme.gizmo.api.client.imdb;

import com.acme.gizmo.api.client.imdb.exceptions.CreateMovieException;
import com.acme.gizmo.api.client.imdb.exceptions.GetMovieException;
import com.acme.gizmo.api.client.imdb.types.CreateMovieRequest;
import com.acme.gizmo.api.client.imdb.types.Movie;
import com.acme.gizmo.api.core.ObjectMappers;
import com.fern.java.jersey.contracts.OptionalAwareContract;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.lang.String;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/movies")
interface MoviesService {
  @POST
  @Path("/create-movie")
  String createMovie(CreateMovieRequest body) throws CreateMovieException;

  @GET
  @Path("/{movieId}")
  Movie getMovie(@PathParam("movieId") String movieId) throws GetMovieException;

  static MoviesService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ObjectMappers.JSON_MAPPER))
        .errorDecoder(new MoviesServiceErrorDecoder()).target(MoviesService.class, url);
  }
}
