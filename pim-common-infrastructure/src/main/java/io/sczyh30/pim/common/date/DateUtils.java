package io.sczyh30.pim.common.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

/**
 * Util class for date and time.
 * This util class works with {@link LocalDate} in JDK 1.8.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public final class DateUtils {

  private DateUtils() {
  }

  /**
   * Default date formatter with format `YYYY/MM/dd`.
   */
  private static final DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
    .appendValue(YEAR, 4, 8, SignStyle.EXCEEDS_PAD)
    .appendLiteral('-')
    .appendValue(MONTH_OF_YEAR, 2)
    .appendLiteral('-')
    .appendValue(DAY_OF_MONTH, 2)
    .toFormatter();

  /**
   * Convert a {@link LocalDate} instance to formatted date string.
   *
   * @param date LocalDate instance
   * @return formatted date string
   */
  public static String dateToString(LocalDate date) {
    return dateFormatter.format(date);
  }

  /**
   * Convert formatted date string to a {@link LocalDate} instance.
   *
   * @param str formatted date string
   * @return LocalDate instance
   */
  public static LocalDate dateFromString(String str) {
    return LocalDate.parse(str, dateFormatter);
  }
}
