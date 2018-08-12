/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cf.jtarget.seminars.model.Seminar;

/**
 * @author dron
 *
 */
public class SeminarIdOnly extends JsonSerializer<Seminar> {

	@Override
	public void serialize(Seminar value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		// Only Id should be serialized here. Rest of Seminar's properties will be
		// serialized into list of all seminars.
		if (value != null) {
			gen.writeNumber(value.getId());
		} else {
			gen.writeNull();
		}

	}

}
