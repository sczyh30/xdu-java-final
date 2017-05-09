package io.sczyh30.pim.client.impl;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.entity.PIMTodo;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.client.WebClient;
import rx.Completable;
import rx.Single;

import java.time.LocalDate;
import java.util.List;

/**
 * Remote HTTP service proxy (client-side) implementation of {@link PimService}.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimRemoteHttpService implements PimService {

  private final Vertx vertx;
  private final WebClient webClient;

  public PimRemoteHttpService(Vertx vertx, JsonObject config) {
    this.vertx = vertx;
    this.webClient = WebClient.create(vertx, new WebClientOptions(config));
  }

  @Override
  public void close() throws Exception {
    webClient.close();
    vertx.close();
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
}
