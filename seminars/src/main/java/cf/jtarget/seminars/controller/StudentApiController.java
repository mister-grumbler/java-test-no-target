/**
 * 
 */
package cf.jtarget.seminars.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.service.ProgressService;
import cf.jtarget.seminars.service.StudentService;

/**
 * ReST/CRUD api controller for student. Implements the following functionality:
 * retrieve list, retrieve by Id, create, update/save, delete by id
 * 
 * @author dron
 *
 */
@RestController
@RequestMapping("/api/student")
public class StudentApiController {

	public static final Logger logger = LoggerFactory.getLogger(StudentApiController.class);
	@Autowired
	StudentService service;
	@Autowired
	ProgressService progressService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> listAllStudents() {
		logger.info("List of all students is requested");
		List<Student> result = service.getAll();
		if (result.isEmpty()) {
			logger.warn("No content for students");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Student>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
		logger.info("Student with Id: {} is requested", id);
		Student result = service.findById(id);
		if (result == null) {
			logger.error("Student with Id: {} is not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
		logger.info("Creating record for new student: {}", student.getName());
		if (service.findByName(student.getName()) != null) {
			logger.error("Student {} is already exist", student.getName());
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		service.save(student);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getId()).toUri());
		return new ResponseEntity<HttpStatus>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> saveStudent(@PathVariable("id") Long id, @RequestBody Student student) {
		logger.info("Updating record for student with Id: {}", id);
		if (student == null || student.getId() != id) {
			logger.error("Invalid content for student with Id: {}", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		if (!service.isExist(id)) {
			logger.error("Unable to update nonexistent student {}", student.getName());
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		service.save(student);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
		logger.info("Deletion of student with Id: {} is requested", id);
		if (service.findById(id) == null) {
			logger.error("Unable to delete nonexistent student with Id: {}", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
