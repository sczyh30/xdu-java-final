package io.sczyh30.pim.entity;

/**
 * Priority enum class.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public enum Priority {

  PHANTOM(-15),
  VERY_LOW(-10),
  LOW(-5),
  NORMAL(0),
  MEDIUM(5),
  HIGH(10),
  URGENT(15),
  EM(100);

  private final int value;

  Priority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
