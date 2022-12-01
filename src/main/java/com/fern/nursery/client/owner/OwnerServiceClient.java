package com.fern.nursery.client.owner;

import com.fern.nursery.client.owner.endpoints.Create;
import com.fern.nursery.client.owner.endpoints.Get;
import com.fern.nursery.client.owner.endpoints.Update;
import com.fern.nursery.client.owner.exceptions.CreateException;
import com.fern.nursery.client.owner.exceptions.GetException;
import com.fern.nursery.client.owner.exceptions.UpdateException;
import com.fern.nursery.client.owner.types.Owner;
import java.lang.String;

public final class OwnerServiceClient {
  private final OwnerService service;

  public OwnerServiceClient(String url) {
    this.service = OwnerService.getClient(url);
  }

  public void create(Create.Request request) throws CreateException {
    this.service.create(request.getBody());
  }

  public Owner get(Get.Request request) throws GetException {
    return this.service.get(request.getOwnerId());
  }

  public Owner update(Update.Request request) throws UpdateException {
    return this.service.update(request.getOwnerId(), request.getBody());
  }
}
