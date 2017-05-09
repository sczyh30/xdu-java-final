package io.sczyh30.pim.common.util;

import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.*;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.Optional;

/**
 * General util class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public final class Utils {

  private Utils() {
  }

  public static JsonObject rawToJson(Object o) {
    return (JsonObject) o;
  }

  public static <T extends PIMEntity> T fromJson(JsonObject json, Class<T> clazz) {
    T result = Json.decodeValue(json.encode(), clazz);
    if (json.containsKey("_id")) {
      result.setId(json.getString("_id"));
    }
    return result;
  }

  /**
   * Construct an PIM entity from a JSON object.
   *
   * @param json json object containing necessary data for PIM entity
   * @return constructed PIMEntity. Return empty if the provided type is unknown
   */
  public static Optional<PIMEntity> entityFromJson(JsonObject json) {
    String type = json.getString("type", "unknown");
    switch (type) {
      case "todo":
        return Optional.of(fromJson(json, PIMTodo.class));
      case "appointment":
        return Optional.of(fromJson(json, PIMAppointment.class));
      case "note":
        return Optional.of(fromJson(json, PIMNote.class));
      case "contact":
        return Optional.of(fromJson(json, PIMContact.class));
      default:
        return Optional.empty();
    }
  }
}
