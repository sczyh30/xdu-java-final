package io.sczyh30.pim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.sczyh30.pim.common.date.LocalDateDeserializer;
import io.sczyh30.pim.common.date.LocalDateSerializer;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;

/**
 * PIM todo entity class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PIMTodo extends PIMEntity implements EntityWithDate {

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;

  private String text;

  public PIMTodo() {
    super();
  }

  public PIMTodo(String text, LocalDate date) {
    super();
    this.text = text;
    this.date = date;
  }

  @Override
  public JsonObject toJson() {
    JsonObject json = JsonObject.mapFrom(this)
      .put("type", "todo");
    return preProcessJson(json);
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
