/**
 * 
 */
package cf.jtarget.seminars.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import cf.jtarget.seminars.model.Student;

/**
 * Custom serializer for {@link cf.jtarget.seminars.model.Student} POJO when it
 * resides in {@link cf.jtarget.seminars.model.Progress} POJO.
 * 
 * Only Id of student instead of whole POJO should be stored as a reference.
 * 
 * @author dron
 *
 */
public class StudentIdOnly extends JsonSerializer<Student> {

	@Override
	public void serialize(Student value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		// Only Id should be serialized here. Rest of Student's properties will be serialized
		// into list of all students.
		if (value != null) {
			gen.writeNumber(value.getId());
		} else {
			gen.writeNull();
		}
	}

}
