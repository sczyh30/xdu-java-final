package io.sczyh30.pim.common.util;

import io.sczyh30.pim.entity.PIMEntity;
import io.sczyh30.pim.entity.*;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * General util class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public final class Utils {

  private Utils() {
  }

  /**
   * Helper method converting raw object directly to JSON.
   *
   * @param o raw object
   * @return converted JSON object
   * @throws ClassCastException when the raw object is not a JSON object
   */
  public static JsonObject rawToJson(Object o) {
    return (JsonObject) o;
  }

  /**
   * Helper method converting a JSON object to a specific entity.
   *
   * @param json  a JSON object
   * @param clazz {@link Class} instance of the entity type
   * @param <T>   entity type
   * @return converted entity
   */
  public static <T extends PIMEntity> T fromJson(JsonObject json, Class<T> clazz) {
    T result = Json.decodeValue(json.encode(), clazz);
    if (json.containsKey("_id")) {
      result.setId(json.getString("_id"));
    }
    return result;
  }

  /**
   * Local version of the `getItemsForDate` method.
   *
   * @param list full entity list
   * @param date pending date
   * @return filtered list
   */
  public static List<EntityWithDate> getItemsForDate(List<PIMEntity> list, LocalDate date) {
    return list.stream()
      .filter(e -> e instanceof EntityWithDate)
      .map(e -> (EntityWithDate) e)
      .filter(e -> e.getDate().isEqual(date))
      .collect(Collectors.toList());
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
