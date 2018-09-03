/**
 * 
 */
package cf.jtarget.seminars.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cf.jtarget.seminars.serializer.RootHolder;
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

	@RequestMapping(value = "/{fileName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> serialize(@PathVariable("fileName") String fileName) {
		logger.info("Creating a full backup in JSON format.");
		String result;
		if (fileName == null || fileName.isEmpty()) {
			fileName = "seminars-backup";
		} else if (fileName.endsWith(".json")) {
			fileName = fileName.substring(0,fileName.length() - ".json".length());;
		}
		RootHolder root = new RootHolder();
		root.setSeminars(seminarService.getAll());
		root.setProfessors(professorService.getAll());
		root.setStudents(studentService.getAll());
		root.setProgresses(progressService.getAll());

		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			result = mapper.writeValueAsString(root);
		} catch (JsonProcessingException e) {
			logger.error("Failed to serialize data due to:", e);
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "filename=\"" + fileName + ".json\"");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		logger.info("Sending {}.json", fileName);
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(result.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(result);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> deserialize(@RequestBody String jsonInput) {
		logger.info("Trying to retrieve data from JSON input.");

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		InjectableValues valuesToInject = new InjectableValues.Std()
				.addValue(StudentService.class.getName(), studentService)
				.addValue(SeminarService.class.getName(), seminarService)
				.addValue(ProfessorService.class.getName(), professorService);
		
		RootHolder root = new RootHolder();
		try {
			root = mapper.setInjectableValues(valuesToInject).readValue(jsonInput.substring(jsonInput.indexOf("{")), RootHolder.class);
		} catch (JsonParseException e) {
			// TODO Add logger message
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			// TODO Add logger message
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			// TODO Add logger message
			e.printStackTrace();
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Saving data into store
		root.getStudents().forEach((student) -> {
			if (!studentService.isExist(student.getId())) {
				studentService.save(student);
			} else logger.info("Student with Id: {} is already exists.", student.getId());
		});
		root.getProfessors().forEach((professor) -> {
			if (!professorService.isExist(professor.getId())) {
				professorService.save(professor);
			} else logger.info("Professor with Id: {} is already exists.", professor.getId());
		});
		root.getSeminars().forEach((seminar) -> {
			if (!seminarService.isExist(seminar.getId())) {
				seminarService.save(seminar);
			} else logger.info("Seminar with Id: {} is already exists.", seminar.getId());
		});
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> diff() {
		logger.info("Trying to compare local data against JSON input.");
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
