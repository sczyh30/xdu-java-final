package io.sczyh30.pim.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vertx.core.json.JsonObject;

/**
 * PIM abstract class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PIMEntity {

  protected String id;

  protected String owner;
  protected boolean shared = true;
  protected Priority priority = Priority.NORMAL;

  public PIMEntity() {
  }

  /**
   * Convert a PIM entity to JSON object.
   *
   * @return converted JSON object
   */
  public abstract JsonObject toJson();

  protected JsonObject preProcessJson(JsonObject json) {
    json.remove("id");
    if (this.id != null) {
      json.put("_id", this.id);
    }
    return json;
  }

  /**
   * Directly cast current object to subclass type T.
   * Warning: this method is not type-safe.
   *
   * @param clazz {@link Class} instance
   * @param <T>   type derived from {@link PIMEntity} class
   * @return cast object
   * @throws ClassCastException if the type T is not compatible with PIMEntity
   */
  @SuppressWarnings("unchecked")
  public <T> T as(Class<T> clazz) {
    return (T) this;
  }

  @Override
  public String toString() {
    return this.toJson().encodePrettily(); // Format as JSON by default.
  }

  // Getter and setter (fluent-style).

  public String getId() {
    return id;
  }

  public PIMEntity setId(String id) {
    this.id = id;
    return this;
  }

  public String getOwner() {
    return owner;
  }

  public PIMEntity setOwner(String owner) {
    this.owner = owner;
    this.shared = false;
    return this;
  }

  public boolean isShared() {
    return shared;
  }

  public PIMEntity setShared(boolean shared) {
    this.shared = shared;
    return this;
  }

  public Priority getPriority() {
    return priority;
  }

  public PIMEntity setPriority(Priority priority) {
    this.priority = priority;
    return this;
  }
}

