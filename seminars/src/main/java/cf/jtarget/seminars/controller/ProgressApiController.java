/**
 * 
 */
package cf.jtarget.seminars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/", params = { "studentId", "seminarId" }, method = RequestMethod.GET)
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
}
