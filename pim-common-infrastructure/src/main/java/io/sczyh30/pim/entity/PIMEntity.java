package io.sczyh30.pim.entity;


import io.vertx.core.json.JsonObject;

/**
 * PIM abstract class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public abstract class PIMEntity {

  protected String owner;
  protected boolean shared;
  protected String priority;

  public PIMEntity() {
    this.priority = "normal";
  }

  public PIMEntity(String priority) {
    this.priority = priority;
  }

  /**
   * Convert a PIM entity to JSON object.
   *
   * @return converted JSON object
   */
  public abstract JsonObject toJson();

  @Override
  public String toString() {
    return this.toJson().encodePrettily(); // Format as JSON.
  }

  // Getter and setter.

  public String getPriority() {
    return this.priority;
  }

  public void setPriority(String p) {
    this.priority = p;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public boolean isShared() {
    return shared;
  }

  public void setShared(boolean shared) {
    this.shared = shared;
  }
}

