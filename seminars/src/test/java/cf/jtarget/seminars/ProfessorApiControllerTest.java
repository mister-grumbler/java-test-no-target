/**
 * 
 */
package cf.jtarget.seminars;

import static org.assertj.core.api.Assertions.assertThat;
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

import cf.jtarget.seminars.model.Professor;
import cf.jtarget.seminars.service.ProfessorService;

/**
 * @author dron
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfessorApiControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Filter springSecurityFilterChain;
	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;

	private MockHttpServletRequestBuilder request;

	@MockBean
	private ProfessorService service;

	private Professor ittem;

	private List<Professor> professorsList;

	/**
	 * Setup of context to support connection over Spring Security
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
		professorsList = new ArrayList<Professor>();
		this.ittem = new Professor();
		ittem.setId((long) 1);
		ittem.setAddress("London");
		ittem.setName("Winston Churchill");
		ittem.setPhone("+4425786512");
		ittem.setSalary((float) 100);
		professorsList.add(ittem);
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProfessorApiController#listAllProfessors()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListAllProfessors() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.get("/api/professor").with(user("user"));
		// Empty list
		when(service.getAll()).thenReturn(new ArrayList<Professor>());
		this.mockMvc.perform(request).andExpect(status().isNoContent());
		// Non empty list
		when(service.getAll()).thenReturn(professorsList);
		this.mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProfessorApiController#getProfessor(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProfessor() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.get("/api/professor/1").with(user("user"));
		// Id is valid
		when(service.isExist((long) 1)).thenReturn(true);
		when(service.findById((long) 1)).thenReturn(this.ittem);
		this.mockMvc.perform(request).andExpect(status().isOk());
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		this.mockMvc.perform(request).andExpect(status().isNotFound());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProfessorApiController#createProfessor(cf.jtarget.seminars.model.Professor, org.springframework.web.util.UriComponentsBuilder)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProfessor() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.post("/api/professor").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		when(service.findByName(ittem.getName())).thenReturn(null);
		this.mockMvc.perform(request).andExpect(status().isCreated());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProfessorApiController#updateProfessor(java.lang.Long, cf.jtarget.seminars.model.Professor)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateProfessor() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.put("/api/professor/1").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		this.mockMvc.perform(request).andExpect(status().isOk());
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		this.mockMvc.perform(request).andExpect(status().isNotFound());
		// Path Id and ittem.id is not equal
		ittem.setId((long) 2);
		request = MockMvcRequestBuilders.put("/api/professor/1").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		when(service.isExist((long) 1)).thenReturn(true);
		this.mockMvc.perform(request).andExpect(status().isNoContent());
		ittem.setId((long) 1);
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.ProfessorApiController#deleteProfessor(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteProfessor() throws Exception {
		assertThat(service).isNotNull();
		request = MockMvcRequestBuilders.delete("/api/professor/1").with(user("user"));
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		this.mockMvc.perform(request).andExpect(status().isOk());
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		this.mockMvc.perform(request).andExpect(status().isNotFound());
	}

}
