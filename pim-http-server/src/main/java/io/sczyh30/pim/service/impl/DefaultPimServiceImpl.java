package io.sczyh30.pim.service.impl;

import io.sczyh30.pim.common.date.DateUtils;
import io.sczyh30.pim.common.util.Utils;
import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.entity.PIMTodo;
import io.sczyh30.pim.service.PimService;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;
import rx.Completable;
import rx.Single;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default server-side implementation of {@link PimService}.
 * The default implementation uses MongoDB as the backend storage.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class DefaultPimServiceImpl implements PimService {

  private static final String COLLECTION = "pimEntity";

  private final Vertx vertx;
  private final MongoClient client;

  public DefaultPimServiceImpl(Vertx vertx, JsonObject config) {
    this.vertx = vertx;
    this.client = MongoClient.createNonShared(vertx, config);
  }

  @Override
  public Single<List<PIMNote>> getNotes() {
    return getNotes(null);
  }

  @Override
  public Single<List<PIMNote>> getNotes(String owner) {
    JsonObject query = new JsonObject().put("type", "note");
    if (owner != null) {
      query.put("owner", owner);
    }
    return client.rxFind(COLLECTION, query)
      .map(r -> r.stream()
        .map(json -> Utils.fromJson(json, PIMNote.class))
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMTodo>> getTodos() {
    return getTodos(null);
  }

  @Override
  public Single<List<PIMTodo>> getTodos(String owner) {
    JsonObject query = new JsonObject().put("type", "todo");
    if (owner != null) {
      query.put("owner", owner);
    }
    return client.rxFind(COLLECTION, query)
      .map(r -> r.stream()
        .map(json -> Utils.fromJson(json, PIMTodo.class))
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMAppointment>> getAppointments() {
    return getAppointments(null);
  }

  @Override
  public Single<List<PIMAppointment>> getAppointments(String owner) {
    JsonObject query = new JsonObject().put("type", "appointment");
    if (owner != null) {
      query.put("owner", owner);
    }
    return client.rxFind(COLLECTION, query)
      .map(r -> r.stream()
        .map(json -> Utils.fromJson(json, PIMAppointment.class))
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMContact>> getContacts() {
    return getContacts(null);
  }

  @Override
  public Single<List<PIMContact>> getContacts(String owner) {
    JsonObject query = new JsonObject().put("type", "contact");
    if (owner != null) {
      query.put("owner", owner);
    }
    return client.rxFind(COLLECTION, query)
      .map(r -> r.stream()
        .map(json -> Utils.fromJson(json, PIMContact.class))
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<EntityWithDate>> getItemsForDate(LocalDate date) {
    return getItemsForDate(date, null);
  }

  @Override
  public Single<List<EntityWithDate>> getItemsForDate(LocalDate date, String owner) {
    JsonObject query = new JsonObject().put("date", DateUtils.dateToString(date));
    if (owner != null) {
      query.put("owner", owner);
    }
    return client.rxFind(COLLECTION, query)
      .map(list -> list.stream()
        .map(Utils::entityFromJson)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(r -> r instanceof EntityWithDate)
        .map(r -> (EntityWithDate) r)
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMEntity>> getAll() {
    return getAllByOwner(null);
  }

  @Override
  public Single<List<PIMEntity>> getAllByOwner(String owner) {
    JsonObject query = new JsonObject();
    if (owner != null) {
      query.put("owner", owner);
    }
    return client.rxFind(COLLECTION, query).toObservable()
      .flatMapIterable(r -> r)
      .map(Utils::entityFromJson)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .toList()
      .toSingle();
  }

  @Override
  public Completable add(PIMEntity entity) {
    JsonObject json = entity.toJson();
    return client.rxInsert(COLLECTION, json)
      .toCompletable();
  }

  @Override
  public Completable update(String id, PIMEntity entity) {
    return add(entity);
  }

  @Override
  public Completable remove(String id) {
    JsonObject query = new JsonObject().put("_id", id);
    return client.rxRemove(COLLECTION, query)
      .toCompletable();
  }

  @Override
  public void close() throws Exception {
    // Here we use the service in a verticle context, so no need to clean-up.
  }
}
