package io.sczyh30.pim.common.util;

import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.*;
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
        return Optional.of(new PIMTodo(json));
      case "appointment":
        return Optional.of(new PIMAppointment(json));
      case "note":
        return Optional.of(new PIMNote(json));
      case "contact":
        return Optional.of(new PIMContact(json));
      default:
        return Optional.empty();
    }
  }
}
