/**
 * 
 */
package cf.jtarget.seminars;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
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

import cf.jtarget.seminars.model.Seminar;
import cf.jtarget.seminars.service.SeminarService;

/**
 * @author dron
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarApiControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Filter springSecurityFilterChain;
	@Autowired
	private ObjectMapper mapper;
	private MockMvc mockMvc;
	private MockHttpServletRequestBuilder request;
	@MockBean
	private SeminarService service;
	private List<Seminar> seminars;
	private Seminar ittem;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
		ittem = new Seminar();
		ittem.setId((long) 1);
		ittem.setName("Math");
		ittem.setNumber(2);
		ittem.setLecturer(null);
		ittem.setFee((float) 100);
		seminars = new ArrayList<Seminar>();
		seminars.add(ittem);
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.SeminarApiController#listSeminars()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListSeminars() throws Exception {
		request = MockMvcRequestBuilders.get("/api/seminar").with(user("user"));
		// Empty list
		when(service.getAll()).thenReturn(new ArrayList<Seminar>());
		mockMvc.perform(request).andExpect(status().isNoContent());
		// Success
		when(service.getAll()).thenReturn(seminars);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.SeminarApiController#getSeminar(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSeminar() throws Exception {
		request = MockMvcRequestBuilders.get("/api/seminar/1").with(user("user"));
		// No such Id
		when(service.findById((long) 1)).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.findById((long) 1)).thenReturn(ittem);
		mockMvc.perform(request).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.SeminarApiController#createSeminar(cf.jtarget.seminars.model.Seminar, org.springframework.web.util.UriComponentsBuilder)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateSeminar() throws Exception {
		request = MockMvcRequestBuilders.post("/api/seminar").with(user("user")).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(ittem));
		// Already exists
		when(service.findByName(ittem.getName())).thenReturn(ittem);
		mockMvc.perform(request).andExpect(status().isConflict());
		// Success
		when(service.findByName(ittem.getName())).thenReturn(null);
		mockMvc.perform(request).andExpect(status().isCreated())
				.andExpect(redirectedUrlPattern("http://*/api/seminar/1"));
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.SeminarApiController#saveSeminar(java.lang.Long, cf.jtarget.seminars.model.Seminar)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveSeminar() throws Exception {
		request = MockMvcRequestBuilders.put("/api/seminar/1").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		mockMvc.perform(request).andExpect(status().isOk());
		// Bad request
		request = MockMvcRequestBuilders.put("/api/seminar/3").with(user("user"))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(ittem));
		mockMvc.perform(request).andExpect(status().isNoContent());
	}

	/**
	 * Test method for
	 * {@link cf.jtarget.seminars.controller.SeminarApiController#deleteSeminar(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteSeminar() throws Exception {
		request = MockMvcRequestBuilders.delete("/api/seminar/1").with(user("user"));
		// No such Id
		when(service.isExist((long) 1)).thenReturn(false);
		mockMvc.perform(request).andExpect(status().isNotFound());
		// Success
		when(service.isExist((long) 1)).thenReturn(true);
		mockMvc.perform(request).andExpect(status().isOk());
	}

}
