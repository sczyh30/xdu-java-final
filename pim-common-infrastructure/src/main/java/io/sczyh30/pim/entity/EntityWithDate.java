package io.sczyh30.pim.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.sczyh30.pim.common.date.DateUtils;

import java.time.LocalDate;

/**
 * Entity interface with date object.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public interface EntityWithDate {

  /**
   * Get the date string.
   */
  @JsonIgnore
  default String getDateString() {
    return DateUtils.dateToString(getDate());
  }

  /**
   * Get the date object.
   */
  LocalDate getDate();

  /**
   * Set the date.
   */
  void setDate(LocalDate date);
}

