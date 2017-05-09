package io.sczyh30.pim.entity;

import io.vertx.core.json.JsonObject;

import java.time.LocalDate;

/**
 * PIM todo entity class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PIMTodo extends PIMEntity implements EntityWithDate {

  private String text;
  private LocalDate date;

  public PIMTodo() {
    super();
  }

  public PIMTodo(JsonObject json) {
    super();
  }

  public PIMTodo(String text, LocalDate date) {
    super();
    this.text = text;
    this.date = date;
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

  public String getText() {
    return text;
  }

  public PIMTodo setText(String text) {
    this.text = text;
    return this;
  }
}
