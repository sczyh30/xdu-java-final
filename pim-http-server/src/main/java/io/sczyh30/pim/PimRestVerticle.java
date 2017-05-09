package io.sczyh30.pim;

import io.sczyh30.pim.common.RestfulApiVerticle;
import io.sczyh30.pim.common.date.DateUtils;
import io.sczyh30.pim.common.util.Utils;
import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.service.PimService;
import io.sczyh30.pim.service.impl.DefaultPimServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The verticle serving the personal information manager RESTful service.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PimRestVerticle extends RestfulApiVerticle {

  private static final String DEFAULT_HOST = "0.0.0.0";
  private static final int DEFAULT_PORT = 8888;

  private static final String API_VERSION = "/api/v";
  private static final String API_ADD = "/api/entities";
  private static final String API_GET_TODOS = "/api/todos";
  private static final String API_GET_APPOINTMENTS = "/api/appointments";
  private static final String API_GET_NOTES = "/api/notes";
  private static final String API_GET_CONTACTS = "/api/contacts";
  private static final String API_GET_ALL = "/api/entities";
  private static final String API_GET_BY_DATE = "/api/date/:date";

  private PimService service;

  @Override
  public void start(Future<Void> startFuture) {
    this.service = new DefaultPimServiceImpl(vertx, config());
    Router router = Router.router(vertx);

    // Add HTTP body handler.
    router.route().handler(BodyHandler.create());

    // API handlers.
    router.get(API_VERSION).handler(this::apiVersion);
    router.post(API_ADD).handler(this::apiAdd);
    router.get(API_GET_TODOS).handler(this::apiGetTodos);
    router.get(API_GET_APPOINTMENTS).handler(this::apiGetAppointments);
    router.get(API_GET_NOTES).handler(this::apiGetNotes);
    router.get(API_GET_CONTACTS).handler(this::apiGetContacts);
    router.get(API_GET_ALL).handler(this::apiGetAll);
    router.get(API_GET_BY_DATE).handler(this::apiGetByDate);

    String host = config().getString("pim.server.http.host", DEFAULT_HOST);
    int port = config().getInteger("pim.server.http.port", DEFAULT_PORT);

    createHttpServer(router, host, port)
      .subscribe(startFuture::complete, startFuture::fail);
  }

  private void apiVersion(RoutingContext context) {
    context.response().end("v1");
  }

  private void apiAdd(RoutingContext context) {
    JsonObject rawEntity = context.getBodyAsJson();
    if (rawEntity != null && rawEntity.containsKey("type")) {
      Optional<PIMEntity> entityOpt = Utils.entityFromJson(rawEntity);
      if (entityOpt.isPresent()) {
        sendResponse(context, service.add(entityOpt.get()));
      } else {
        badRequest(context);
      }
    } else {
      badRequest(context);
    }
  }

  private void apiGetAll(RoutingContext context) {
    String owner = context.request().getParam("owner");
    if (owner != null) {
      sendResponse(context, service.getAllByOwner(owner), Json::encodePrettily);
    } else {
      sendResponse(context, service.getAll(), Json::encodePrettily);
    }
  }

  private void apiGetTodos(RoutingContext context) {
    String owner = context.request().getParam("owner");
    if (owner != null) {
      sendResponse(context, service.getTodos(owner), Json::encodePrettily);
    } else {
      sendResponse(context, service.getTodos(), Json::encodePrettily);
    }
  }

  private void apiGetAppointments(RoutingContext context) {
    String owner = context.request().getParam("owner");
    if (owner != null) {
      sendResponse(context, service.getAppointments(owner), Json::encodePrettily);
    } else {
      sendResponse(context, service.getAppointments(), Json::encodePrettily);
    }
  }

  private void apiGetNotes(RoutingContext context) {
    String owner = context.request().getParam("owner");
    if (owner != null) {
      sendResponse(context, service.getNotes(owner), Json::encodePrettily);
    } else {
      sendResponse(context, service.getNotes(), Json::encodePrettily);
    }
  }

  private void apiGetContacts(RoutingContext context) {
    String owner = context.request().getParam("owner");
    if (owner != null) {
      sendResponse(context, service.getContacts(owner), Json::encodePrettily);
    } else {
      sendResponse(context, service.getContacts(), Json::encodePrettily);
    }
  }

  private void apiGetByDate(RoutingContext context) {
    try {
      String rawDate = context.request().getParam("date");
      if (rawDate == null) {
        badRequest(context);
      } else {
        String owner = context.request().getParam("owner");
        LocalDate date = DateUtils.dateFromString(rawDate);
        if (owner != null) {
          sendResponse(context, service.getItemsForDate(date, owner), Json::encodePrettily);
        } else {
          sendResponse(context, service.getItemsForDate(date), Json::encodePrettily);
        }
      }
    } catch (Exception ex) {
      badRequest(context, ex);
    }
  }
}
