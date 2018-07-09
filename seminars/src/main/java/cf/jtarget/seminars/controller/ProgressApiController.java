/**
 * 
 */
package cf.jtarget.seminars.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Progress>> listProgress(@PathVariable("seminarId") Long seminarId) {

		List<Progress> result = service.findBySeminar(seminarService.findById(seminarId));
		if (!seminarService.isExist(seminarId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Progress>>(result, HttpStatus.OK);
	}

	@RequestMapping(params = { "studentId" }, method = RequestMethod.GET)
	public ResponseEntity<List<Progress>> listByStudent(@PathVariable("studentId") Long studentId) {
		if (!studentService.isExist(studentId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Progress> result = service.findByStudent(studentService.findById(studentId));
		return new ResponseEntity<List<Progress>>(result, HttpStatus.OK);
	}

}
