package io.sczyh30.pim.entity;

import io.vertx.core.json.JsonObject;

/**
 * PIM contact entity.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class PIMContact extends PIMEntity {

  private String firstName;
  private String lastName;
  private String email;

  public PIMContact() {
    super();
  }

  public PIMContact(JsonObject json) {

  }

  public PIMContact(String firstName, String lastName, String email) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public PIMContact(String priority, String firstName, String lastName, String email) {
    super(priority);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  @Override
  public JsonObject toJson() {
    JsonObject json = new JsonObject();

    return json;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

