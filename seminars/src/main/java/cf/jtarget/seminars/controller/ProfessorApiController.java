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

import cf.jtarget.seminars.model.Professor;
import cf.jtarget.seminars.service.ProfessorService;

/**
 * @author dron
 *
 */
@RestController
@RequestMapping("/api/professor")
public class ProfessorApiController {
	public static final Logger logger = LoggerFactory.getLogger(ProfessorApiController.class);
	@Autowired
	ProfessorService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Professor>> listAllProfessors() {
		List<Professor> result = service.getAll();
		if (result.isEmpty()) {
			logger.error("No records for professor is exists");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Professor>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Professor> getProfessor(@PathVariable("id") Long id) {
		logger.info("Record for professor with Id: {} is requested", id);
		if (!service.isExist(id)) {
			logger.error("Professor with Id: {} is not found", id);
			return new ResponseEntity<Professor>(HttpStatus.NOT_FOUND);
		}
		Professor result = service.findById(id);
		return new ResponseEntity<Professor>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> createProfessor(@RequestBody Professor professor,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating new professor {}", professor.getName());
		if (service.findByName(professor.getName()) != null) {
			logger.error("Record for professof {} is already exist", professor.getName());
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		service.save(professor);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/professor/{id}").buildAndExpand(professor.getId()).toUri());
		return new ResponseEntity<HttpStatus>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateProfessor(@PathVariable("id") Long id, @RequestBody Professor professor) {
		logger.info("Updating record for professor with Id: {}", id);
		if (!service.isExist(id)) {
			logger.error("Professor with Id: {} is not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (professor.getId() != id) {
			logger.error("Invalid date for professor {} with Id: {}", professor.getName(), id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		service.save(professor);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteProfessor(@PathVariable("id") Long id) {
		logger.info("Deleteng professor with Id: {}", id);
		if (!service.isExist(id)) {
			logger.error("Professor with Id: {} is not found", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
