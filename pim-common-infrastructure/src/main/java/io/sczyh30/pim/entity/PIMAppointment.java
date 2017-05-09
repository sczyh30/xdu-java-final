package io.sczyh30.pim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.sczyh30.pim.common.date.LocalDateDeserializer;
import io.sczyh30.pim.common.date.LocalDateSerializer;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;

/**
 * PIM appointment entity.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PIMAppointment extends PIMEntity implements EntityWithDate {

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;

  private String description;

  public PIMAppointment() {
    super();
  }

  public PIMAppointment(LocalDate date, String description) {
    super();
    this.date = date;
    this.description = description;
  }

  @Override
  public JsonObject toJson() {
    JsonObject json = JsonObject.mapFrom(this)
      .put("type", "appointment");
    return preProcessJson(json);
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

  public PIMAppointment setDescription(String description) {
    this.description = description;
    return this;
  }
}
