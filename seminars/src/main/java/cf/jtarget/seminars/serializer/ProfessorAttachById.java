/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import cf.jtarget.seminars.model.Professor;
import cf.jtarget.seminars.service.ProfessorService;

/**
 * Deserialization supporting class. Used to attach appropriate instance of
 * Professor object to deserialized instance of Seminar object. If there is no
 * access to instance of ProfessorService then bare instance of Professor object
 * is attached.
 * 
 * @author dron
 *
 */
public class ProfessorAttachById extends JsonDeserializer<Professor> {

	@Override
	public Professor deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Long id = p.getLongValue();
		ProfessorService service = (ProfessorService) ctxt.findInjectableValue(ProfessorService.class.getName(), null,
				null);
		if (service == null) {
			Professor s = new Professor();
			s.setId(id);
			return s;
		}
		if (service.isExist(id)) {
			return service.findById(id);
		} else {
			return null;
		}
	}

}
