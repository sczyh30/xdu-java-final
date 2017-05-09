package io.sczyh30.pim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vertx.core.json.JsonObject;

/**
 * PIM note entity class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PIMNote extends PIMEntity {

  private String text;

  public PIMNote() {
    super();
  }

  public PIMNote(String text) {
    super();
    this.text = text;
  }

  @Override
  public JsonObject toJson() {
    JsonObject json = JsonObject.mapFrom(this)
      .put("type", "note");
    return preProcessJson(json);
  }

  public String getText() {
    return text;
  }

  public PIMNote setText(String text) {
    this.text = text;
    return this;
  }
}
