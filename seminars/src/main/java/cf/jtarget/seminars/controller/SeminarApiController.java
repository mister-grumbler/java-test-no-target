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

import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.service.ProgressService;
import cf.jtarget.seminars.service.SeminarService;

/**
 * @author dron
 *
 */
@RestController
@RequestMapping("/seminar")
public class SeminarApiController {

	public static final Logger logger = LoggerFactory.getLogger(SeminarApiController.class);

	@Autowired
	SeminarService service;
	@Autowired
	ProgressService progressService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Seminar>> listSeminars() {
		logger.info("List of seminars is requested");
		List<Seminar> result = service.getAll();
		if (result.isEmpty()) {
			logger.warn("No content for seminars");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Seminar>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Seminar> getSeminar(@PathVariable("id") Long id) {
		logger.info("Seminar with Id: {} is requested", id);
		Seminar seminar = service.findById(id);
		if (seminar == null) {
			logger.error("Seminar with Id: {} is not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Seminar>(seminar, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> createSeminar(@RequestBody Seminar seminar, UriComponentsBuilder ucBuilder) {
		logger.info("Creating record for seminar {}", seminar.getName());
		if (service.findByName(seminar.getName()) != null) {
			logger.error("Seminar {} is already exist", seminar.getName());
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		service.save(seminar);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/seminar/{id}").buildAndExpand(seminar.getId()).toUri());
		return new ResponseEntity<HttpStatus>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> saveSeminar(@PathVariable("id") Long id, @RequestBody Seminar seminar) {
		logger.info("Updating record for seminar with Id: {}", id);
		if (seminar.getId() != id) {
			logger.error("Invalid content for seminar with Id: {}", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		if (!service.isExist(id)) {
			logger.error("Seminar with Id: {} is not exist", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteSeminar(@PathVariable("id") Long id) {
		logger.info("Deleteng seminar with Id: {}", id);
		if (!service.isExist(id)) {
			logger.error("Seminar with Id: {} is not found", id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
