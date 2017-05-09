package io.sczyh30.pim.client.impl;

import io.sczyh30.pim.client.PimService;
import io.sczyh30.pim.common.util.PimServiceException;
import io.sczyh30.pim.common.util.Utils;
import io.sczyh30.pim.entity.EntityWithDate;
import io.sczyh30.pim.entity.PIMAppointment;
import io.sczyh30.pim.entity.PIMContact;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.PIMNote;
import io.sczyh30.pim.entity.PIMTodo;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.buffer.Buffer;
import io.vertx.rxjava.ext.web.client.HttpRequest;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Completable;
import rx.Single;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Remote HTTP service proxy (client-side) implementation of {@link PimService}.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimRemoteHttpService implements PimService {

  private static final String DEFAULT_HOST = "0.0.0.0";
  private static final int DEFAULT_PORT = 8888;

  private static final String API_ADD = "/api/entities";
  private static final String API_GET_TODOS = "/api/todos";
  private static final String API_GET_APPOINTMENTS = "/api/appointments";
  private static final String API_GET_NOTES = "/api/notes";
  private static final String API_GET_CONTACTS = "/api/contacts";
  private static final String API_GET_ALL = "/api/entities";
  private static final String API_GET_BY_DATE = "/api/date";

  private final Vertx vertx;
  private final WebClient webClient;

  private final String host;
  private final int port;

  public PimRemoteHttpService(Vertx vertx, JsonObject config) {
    this.vertx = vertx;
    this.webClient = WebClient.create(vertx, new WebClientOptions(config));
    this.host = config.getString("pim.server.http.host", DEFAULT_HOST);
    this.port = config.getInteger("pim.server.http.port", DEFAULT_PORT);
  }

  @Override
  public void close() throws Exception {
    webClient.close();
    vertx.close();
  }

  /**
   * Resolve the response and build the {@link Single} instance.
   *
   * @param response HTTP response
   * @return generated {@link Single} instance
   */
  private <T> Single<T> resolveResponse(HttpResponse<T> response) {
    int status = response.statusCode();
    if (status >= 400) {
      return Single.error(new PimServiceException(status, response.bodyAsString()));
    } else {
      return Single.just(response.body()); // TODO: Should we set a scheduler in Vert.x context?
    }
  }

  @Override
  public Single<List<PIMNote>> getNotes() {
    return getNotes(null);
  }

  @Override
  public Single<List<PIMNote>> getNotes(String owner) {
    HttpRequest<Buffer> request = webClient.get(port, host, API_GET_NOTES);
    if (owner != null) {
      request = request.addQueryParam("owner", owner);
    }
    return request.as(BodyCodec.jsonArray())
      .rxSend()
      .flatMap(this::resolveResponse)
      .map(r -> r.stream()
        .map(Utils::rawToJson)
        .map(PIMNote::new)
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMTodo>> getTodos() {
    return getTodos(null);
  }

  @Override
  public Single<List<PIMTodo>> getTodos(String owner) {
    HttpRequest<Buffer> request = webClient.get(port, host, API_GET_TODOS);
    if (owner != null) {
      request = request.addQueryParam("owner", owner);
    }
    return request.as(BodyCodec.jsonArray())
      .rxSend()
      .flatMap(this::resolveResponse)
      .map(r -> r.stream()
        .map(Utils::rawToJson)
        .map(PIMTodo::new)
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMAppointment>> getAppointments() {
    return getAppointments(null);
  }

  @Override
  public Single<List<PIMAppointment>> getAppointments(String owner) {
    HttpRequest<Buffer> request = webClient.get(port, host, API_GET_APPOINTMENTS);
    if (owner != null) {
      request = request.addQueryParam("owner", owner);
    }
    return request.as(BodyCodec.jsonArray())
      .rxSend()
      .flatMap(this::resolveResponse)
      .map(r -> r.stream()
        .map(Utils::rawToJson)
        .map(PIMAppointment::new)
        .collect(Collectors.toList())
      );
  }

  @Override
  public Single<List<PIMContact>> getContacts() {
    return getContacts(null);
  }

  @Override
  public Single<List<PIMContact>> getContacts(String owner) {
    HttpRequest<Buffer> request = webClient.get(port, host, API_GET_CONTACTS);
    if (owner != null) {
      request = request.addQueryParam("owner", owner);
    }
    return request.as(BodyCodec.jsonArray())
      .rxSend()
      .flatMap(this::resolveResponse)
      .map(r -> r.stream()
        .map(Utils::rawToJson)
        .map(PIMContact::new)
        .collect(Collectors.toList())
      );
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
    return getAllByOwner(null);
  }

  @Override
  public Single<List<PIMEntity>> getAllByOwner(String owner) {
    HttpRequest<Buffer> request = webClient.get(port, host, API_GET_ALL);
    if (owner != null) {
      request = request.addQueryParam("owner", owner);
    }
    return request.as(BodyCodec.jsonArray())
      .rxSend()
      .flatMap(this::resolveResponse)
      .map(r -> r.stream()
        .map(Utils::rawToJson)
        .map(Utils::entityFromJson)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList())
      );
  }

  @Override
  public Completable add(PIMEntity entity) {
    return webClient.post(port, host, API_ADD)
      .rxSendJsonObject(entity.toJson())
      .flatMap(this::resolveResponse)
      .toCompletable();
  }
}
