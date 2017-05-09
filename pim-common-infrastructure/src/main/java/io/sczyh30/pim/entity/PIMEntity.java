package io.sczyh30.pim.entity;


import io.vertx.core.json.JsonObject;

/**
 * PIM abstract class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public abstract class PIMEntity {

  protected String id;
  protected String owner;
  protected boolean shared;
  protected String priority;

  public PIMEntity() {
    this.priority = "normal";
  }

  /**
   * Convert a PIM entity to JSON object.
   *
   * @return converted JSON object
   */
  public abstract JsonObject toJson();

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

  public String getPriority() {
    return priority;
  }

  public PIMEntity setPriority(String priority) {
    this.priority = priority;
    return this;
  }
}

