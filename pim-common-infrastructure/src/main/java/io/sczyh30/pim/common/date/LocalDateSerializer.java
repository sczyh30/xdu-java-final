package io.sczyh30.pim.common.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Jackson serializer for {@link LocalDate}.
 *
 * @author <a href="http://www.sczyh30.com">Eric Zhao 14130140389</a>
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

  private static final long serialVersionUID = 1L;

  public LocalDateSerializer() {
    super(LocalDate.class);
  }

  @Override
  public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeString(DateUtils.dateToString(value));
  }
}
