/**
 * 
 */
package cf.jtarget.seminars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

import cf.jtarget.seminars.model.Progress;
import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.service.ProgressService;
import cf.jtarget.seminars.service.SeminarService;
import cf.jtarget.seminars.service.StudentService;

/**
 * @author dron
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgressApiControllerTest {

	@Autowired
	private Filter springSecurityFilterChain;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ObjectMapper mapper;
	private MockMvc mockMvc;
	private MockHttpServletRequestBuilder request;
	@MockBean
	private StudentService studentServ;
	@MockBean
	private SeminarService seminarServ;
	@MockBean
	private ProgressService service;
	private Progress ittem;
	private List<Progress> progressList;
	private Seminar seminar;
	private Student student;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
		InjectableValues valuesToInject = new InjectableValues.Std()
				.addValue(StudentService.class.getName(), studentServ)
				.addValue(SeminarService.class.getName(), seminarServ);
		mapper.setInjectableValues(valuesToInject);
		List<Integer> marks;
		marks = new ArrayList<Integer>();
		marks.add(4);
		marks.add(3);
		marks.add(5);
		marks.add(4);
		progressList = new ArrayList<Progress>();
		seminar = new Seminar();
		seminar.setId((long) 1);
		seminar.setName("Math");
		seminar.setNumber(2);
		seminar.setLecturer(null);
		seminar.setFee((float) 100);
		student = new Student();
		student.setId((long) 1);
		student.setName("Ano Ny Mouse");
		student.setAddress("Kt st 31");
		student.setEmail("anm@gmail.com");
		student.setPhone("+41987654321");
		student.setMarksBook(32768);
		student.setMarksAverage((float) 4);
		ittem = new Progress();
		ittem.setId((long) 1);
		ittem.setMarks(marks);
		ittem.setSeminar(seminar);
		ittem.setStudent(student);
		progressList.add(ittem);
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProgressApiController#getByIds(java.lang.Long, java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetByIds() throws Exception {
		assertThat(service).isNotNull();
		assertThat(seminarServ).isNotNull();
		assertThat(studentServ).isNotNull();
		request = MockMvcRequestBuilders.get("/api/progress").with(user("user")).param("studentId", "1")
				.param("seminarId", "1");
		// Success
		when(studentServ.isExist((long) 1)).thenReturn(true);
		when(seminarServ.isExist((long) 1)).thenReturn(true);
		when(service.findByIds((long) 1, (long) 1)).thenReturn(ittem);
		mockMvc.perform(request).andExpect(status().isOk());
		// No such studentId
		when(studentServ.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// No such seminarId
		when(studentServ.isExist((long) 1)).thenReturn(true);
		when(seminarServ.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// No such progress record
		when(seminarServ.isExist((long) 1)).thenReturn(true);
		when(service.findByIds((long) 1, (long) 1)).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isNotFound());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProgressApiController#listBySeminar(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListBySeminar() throws Exception {
		assertThat(service).isNotNull();
		assertThat(seminarServ).isNotNull();
		assertThat(studentServ).isNotNull();
		request = MockMvcRequestBuilders.get("/api/progress").with(user("user")).param("seminarId", "1");
		// No such seminarId
		when(seminarServ.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// No progress record available
		when(seminarServ.isExist((long) 1)).thenReturn(true);
		when(seminarServ.findById((long) 1)).thenReturn(seminar);
		when(service.findBySeminarId((long) 1)).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(seminarServ.isExist((long) 1)).thenReturn(true);
		when(seminarServ.findById((long) 1)).thenReturn(seminar);
		when(service.findBySeminarId((long) 1)).thenReturn(progressList);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProgressApiController#listByStudent(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListByStudent() throws Exception {
		assertThat(service).isNotNull();
		assertThat(seminarServ).isNotNull();
		assertThat(studentServ).isNotNull();
		request = MockMvcRequestBuilders.get("/api/progress").with(user("user")).param("studentId", "1");
		// No such studentId
		when(studentServ.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// No progress record available
		when(studentServ.isExist((long) 1)).thenReturn(true);
		when(studentServ.findById((long) 1)).thenReturn(student);
		when(service.findByStudentId((long) 1)).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.findByStudentId((long) 1)).thenReturn(progressList);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProgressApiController#createProgress(Progress, org.springframework.web.util.UriComponentsBuilder)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProgress() throws Exception {
		assertThat(service).isNotNull();
		assertThat(ittem.getStudent()).isNotNull();
		assertThat(ittem.getStudent().getId()).isNotNull();
		assertThat(ittem.getSeminar()).isNotNull();
		assertThat(ittem.getSeminar().getId()).isNotNull();
		request = MockMvcRequestBuilders.post("/api/progress").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		// Record for this progress is exist
		when(service.findByIds(ittem.getStudent().getId(), ittem.getSeminar().getId())).thenReturn(ittem);
		mockMvc.perform(request).andExpect(status().isConflict()).andDo(print());
		// Success
		when(service.findByIds(ittem.getStudent().getId(), ittem.getSeminar().getId())).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isCreated());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProgressApiController#updateProgress(Long, Progress)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateProgress() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.put("/api/progress/1").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		when(seminarServ.findById((long) 1)).thenReturn(seminar);
		when(studentServ.findById((long) 1)).thenReturn(student);
		this.mockMvc.perform(request).andExpect(status().isOk());
		// No such progress
		when(service.isExist((long) 1)).thenReturn(false);
		this.mockMvc.perform(request).andExpect(status().isNotFound());
		// Bad request. Identifiers are not equals.
		request = MockMvcRequestBuilders.put("/api/progress/2").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		this.mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProgressApiController#deleteProgress(Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteProgress() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.delete("/api/progress/1").with(user("user"));
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		mockMvc.perform(request).andExpect(status().isOk());
	}
}
