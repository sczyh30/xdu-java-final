package io.sczyh30.pim.entity;

import io.vertx.core.json.JsonObject;

import java.time.LocalDate;

/**
 * PIM appointment entity.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PIMAppointment extends PIMEntity implements EntityWithDate {

  private LocalDate date;
  private String description;

  public PIMAppointment() {
    super();
  }

  public PIMAppointment(JsonObject json) {

  }

  public PIMAppointment(LocalDate date, String description) {
    super();
    this.date = date;
    this.description = description;
  }

  public PIMAppointment(String priority, LocalDate date, String description) {
    super(priority);
    this.date = date;
    this.description = description;
  }

  @Override
  public JsonObject toJson() {
    JsonObject json = new JsonObject();

    return json;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
