/**
 * 
 */
package cf.jtarget.seminars.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cf.jtarget.seminars.model.Professor;
import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.service.ProfessorService;
import cf.jtarget.seminars.service.ProgressService;
import cf.jtarget.seminars.service.SeminarService;
import cf.jtarget.seminars.service.StudentService;

/**
 * Controller that handles backup, restore and differences checking
 * functionality.
 * 
 * @author dron
 *
 */
@RestController
@RequestMapping("/api/backup")
public class BackupApiController {
	private static final Logger logger = LoggerFactory.getLogger(BackupApiController.class);
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private SeminarService seminarService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProgressService progressService;
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> backup() {
		logger.info("Creating a full backup in JSON format.");
		Map<String, List> fullSet = new HashMap<String, List>();
		List<Seminar> seminars = seminarService.getAll();
		List<Professor> professors = professorService.getAll();
		List<Student> students = studentService.getAll();
		List<Progress> progresses = progressService.getAll();
		fullSet.put("students", students);
		fullSet.put("seminars", seminars);
		fullSet.put("professors", professors);
		fullSet.put("progresses", progresses);
		
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			return new ResponseEntity<>(mapper.writeValueAsString(fullSet), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> restore() {
		logger.info("Trying to retrieve data from JSON input.");
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> diff() {
		logger.info("Trying to compare local data against JSON input.");
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
