package io.sczyh30.pim.gui;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.client.impl.PimRemoteHttpService;
import io.sczyh30.pim.entity.PIMEntity;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Service context class. Thread-safe singleton.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public final class PimServiceContext {

  private PimServiceContext() {
  }

  private static final PimServiceContext CONTEXT = new PimServiceContext();
  private static final PimService SERVICE = new PimRemoteHttpService(Vertx.vertx(), new JsonObject());

  private volatile List<PIMEntity> entity = new CopyOnWriteArrayList<>();
  private volatile String owner;

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    synchronized (this) {
      this.owner = owner;
    }
  }

  public List<PIMEntity> getEntityList() {
    return entity;
  }

  public void setEntityList(List<PIMEntity> list) {
    synchronized (this) {
      this.entity = new CopyOnWriteArrayList<>(list);
    }
  }

  public static PimServiceContext getContext() {
    return CONTEXT;
  }

  public static PimService getService() {
    return SERVICE;
  }
}
