/**
 * 
 */
package cf.jtarget.seminars;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import cf.jtarget.seminars.model.Student;
import cf.jtarget.seminars.service.StudentService;

/**
 * @author dron
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentApiControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Filter springSecurityFilterChain;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private StudentService service;
	private MockMvc mockMvc;
	private MockHttpServletRequestBuilder request;
	private List<Student> students;
	private Student ittem;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
		ittem = new Student();
		ittem.setId((long) 1);
		ittem.setName("");
		ittem.setAddress("");
		ittem.setEmail("");
		ittem.setPhone("");
		ittem.setMarksBook(32768);
		ittem.setMarksAverage((float) 4);
		students = new ArrayList<Student>();
		students.add(ittem);
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.StudentApiController#listAllStudents()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListAllStudents() throws Exception {
		request = MockMvcRequestBuilders.get("/api/student").with(user("user"));
		// Empty list
		when(service.getAll()).thenReturn(new ArrayList<Student>());
		mockMvc.perform(request).andExpect(status().isNoContent());
		// Not empty list
		when(service.getAll()).thenReturn(students);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.StudentApiController#getStudent(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetStudent() throws Exception {
		request = MockMvcRequestBuilders.get("/api/student/1").with(user("user"));
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		when(service.findById((long) 1)).thenReturn(ittem);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.StudentApiController#createStudent(cf.jtarget.seminars.model.Student, org.springframework.web.util.UriComponentsBuilder)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateStudent() throws Exception {
		request = MockMvcRequestBuilders.post("/api/student").with(user("user")).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(ittem));
		// Success
		when(service.findByName(ittem.getName())).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isCreated());
		// Name is already exist
		when(service.findByName(ittem.getName())).thenReturn(ittem);
		mockMvc.perform(request).andExpect(status().isConflict());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.StudentApiController#saveStudent(java.lang.Long, cf.jtarget.seminars.model.Student)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveStudent() throws Exception {
		request = MockMvcRequestBuilders.put("/api/student/1").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		mockMvc.perform(request).andExpect(status().isOk());
		// Id in URI is not equals Id in JSON
		request = MockMvcRequestBuilders.put("/api/student/2").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		 mockMvc.perform(request).andExpect(status().isNoContent());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.StudentApiController#deleteStudent(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteStudent() throws Exception {
		request = MockMvcRequestBuilders.delete("/api/student/1").with(user("user"));
		// Not exist
		when(service.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		mockMvc.perform(request).andExpect(status().isOk());
	}

}
