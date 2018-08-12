/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cf.jtarget.seminars.model.Professor;

/**
 * @author dron
 *
 */
public class ProfessorIdOnly extends JsonSerializer<Professor> {

	@Override
	public void serialize(Professor value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		// Only Id should be serialized here. Rest of Professor's properties will be
		// serialized into list of all professors.
		if (value != null) {
			gen.writeNumber(value.getId());
		} else {
			gen.writeNull();
		}
	}

}
