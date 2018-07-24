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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.service.ProgressService;
import cf.jtarget.seminars.service.SeminarService;
import cf.jtarget.seminars.service.StudentService;

/**
 * @author dron
 *
 */
@RestController
@RequestMapping("/api/progress")
public class ProgressApiController {

	public static final Logger logger = LoggerFactory.getLogger(ProfessorApiController.class);

	@Autowired
	ProgressService service;
	@Autowired
	SeminarService seminarService;
	@Autowired
	StudentService studentService;

	@RequestMapping(params = { "studentId", "seminarId" }, method = RequestMethod.GET)
	public ResponseEntity<Progress> getByIds(@RequestParam("studentId") Long studentId,
			@RequestParam("seminarId") Long seminarId) {

		if (!studentService.isExist(studentId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (!seminarService.isExist(seminarId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Progress result = service.findByIds(studentId, seminarId);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Progress>(result, HttpStatus.OK);
	}

	@RequestMapping(params = { "seminarId" }, method = RequestMethod.GET)
	public ResponseEntity<List<Progress>> listBySeminar(@RequestParam("seminarId") Long seminarId) {

		if (!seminarService.isExist(seminarId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Progress> result = service.findBySeminarId(seminarId);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Progress>>(result, HttpStatus.OK);
	}

	@RequestMapping(params = { "studentId" }, method = RequestMethod.GET)
	public ResponseEntity<List<Progress>> listByStudent(@RequestParam("studentId") Long studentId) {
		if (!studentService.isExist(studentId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Progress> result = service.findByStudentId(studentId);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Progress>>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> createProgress(@RequestBody Progress progress, UriComponentsBuilder ucBuilder) {
		if (service.findByIds(progress.getStudent().getId(), progress.getSeminar().getId()) != null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		service.save(progress);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/progress").queryParam("studentId", progress.getStudent().getId())
				.queryParam("seminarId", progress.getSeminar().getId()).build().toUri());
		return new ResponseEntity<HttpStatus>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> updateProgress(@PathVariable("id") Long id, @RequestBody Progress progress) {
		if (progress.getId() != id) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		if (!service.isExist(id)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		service.save(progress);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteProgress(@PathVariable("id") Long id) {
		if (!service.isExist(id)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
