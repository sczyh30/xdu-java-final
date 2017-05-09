package io.sczyh30.pim.service.impl;

import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.entity.PIMTodo;
import io.sczyh30.pim.service.PimService;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import rx.Completable;
import rx.Single;

import java.time.LocalDate;
import java.util.List;

/**
 * Default server-side implementation of {@link PimService}.
 * The default implementation uses MySQL as the backend storage.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class DefaultPimServiceImpl implements PimService {

  private final Vertx vertx;
  private final JDBCClient client;

  public DefaultPimServiceImpl(Vertx vertx, JsonObject config) {
    this.vertx = vertx;
    this.client = JDBCClient.createNonShared(vertx, config);
  }

  @Override
  public Single<List<PIMNote>> getNotes() {
    return null;
  }

  @Override
  public Single<List<PIMNote>> getNotes(String owner) {
    return null;
  }

  @Override
  public Single<List<PIMTodo>> getTodos() {
    return null;
  }

  @Override
  public Single<List<PIMTodo>> getTodos(String owner) {
    return null;
  }

  @Override
  public Single<List<PIMAppointment>> getAppointments() {
    return null;
  }

  @Override
  public Single<List<PIMAppointment>> getAppointments(String owner) {
    return null;
  }

  @Override
  public Single<List<PIMContact>> getContacts() {
    return null;
  }

  @Override
  public Single<List<PIMContact>> getContacts(String owner) {
    return null;
  }

  @Override
  public Single<List<EntityWithDate>> getItemsForDate(LocalDate date) {
    return null;
  }

  @Override
  public Single<List<EntityWithDate>> getItemsForDate(LocalDate date, String owner) {
    return null;
  }

  @Override
  public Single<List<PIMEntity>> getAll() {
    return null;
  }

  @Override
  public Single<List<PIMEntity>> getAllByOwner(String owner) {
    return null;
  }

  @Override
  public Completable add(PIMEntity entity) {
    return null;
  }

  @Override
  public void close() throws Exception {
    // Here we use the service in a verticle context, so no need to clean-up.
  }
}
