package io.sczyh30.pim.common.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Jackson deserializer for {@link LocalDate}.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

  private static final long serialVersionUID = 1L;

  protected LocalDateDeserializer() {
    super(LocalDate.class);
  }

  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
    return DateUtils.dateFromString(p.readValueAs(String.class));
  }
}
