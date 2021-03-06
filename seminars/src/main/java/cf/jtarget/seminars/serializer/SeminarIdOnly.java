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
 * Custom serializer for {@link cf.jtarget.seminars.model.Seminar} POJO when it
 * resides in {@link cf.jtarget.seminars.model.Progress} POJO
 * 
 * Only Id of seminar instead of whole POJO should be stored as a reference.
 * 
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
