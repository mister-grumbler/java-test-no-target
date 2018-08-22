/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.service.StudentService;

/**
 * Deserialization supporting class. Used to attach appropriate instance of
 * Student object to deserialized instance of Progress object. If there is no
 * access to instance of StudentService or appropriate instance of Student not
 * exist then bare instance of Student object is attached.
 * 
 * @author dron
 *
 */
public class StudentAttachById extends JsonDeserializer<Student> {

	@Override
	public Student deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Long id = p.getLongValue();
		StudentService service = (StudentService) ctxt.findInjectableValue(StudentService.class.getName(), null, null);
		if (service == null || !service.isExist(id)) {
			Student s = new Student();
			s.setId(id);
			return s;
		}
		return service.findById(id);
	}

}
