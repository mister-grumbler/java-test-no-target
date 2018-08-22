/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.service.SeminarService;

/**
 * Deserialization supporting class. Used to attach appropriate instance of
 * Seminar object to deserialized instance of Progress object. If there is no
 * access to instance of SeminarService or appropriate instance of Seminar not
 * exist then bare instance of Seminar object is attached.
 * 
 * @author dron
 *
 */
public class SeminarAttachById extends JsonDeserializer<Seminar> {

	@Override
	public Seminar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Long id = p.getLongValue();
		SeminarService service = (SeminarService) ctxt.findInjectableValue(SeminarService.class.getName(), null, null);
		if (service == null || !service.isExist(id)) {
			Seminar s = new Seminar();
			s.setId(id);
			return s;
		}
		return service.findById(id);
	}

}
