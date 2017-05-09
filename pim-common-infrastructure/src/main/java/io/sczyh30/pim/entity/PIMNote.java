package io.sczyh30.pim.entity;

import io.vertx.core.json.JsonObject;

/**
 * PIM note entity class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PIMNote extends PIMEntity {

  private String text;

  public PIMNote() {
    super();
  }

  public PIMNote(JsonObject json) {

  }

  public PIMNote(String text) {
    super();
    this.text = text;
  }

  @Override
  public JsonObject toJson() {
    JsonObject json = new JsonObject();

    return json;
  }

  public String getText() {
    return text;
  }

  public PIMNote setText(String text) {
    this.text = text;
    return this;
  }
}
