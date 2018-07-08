/**
 * 
 */
package cf.jtarget.seminars;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author dron
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarsAppTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Filter springSecurityFilterChain;
	private MockMvc mockMvc;

	/**
	 * Setup of context to support connection over Spring Security
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void contextLoads() throws Exception {
		return;
	}

	/**
	 * Test for MockMvc initialization
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDefaultResponse() throws Exception {
		this.mockMvc.perform(get("/").with(user("user")))
				// Ensure we got past Security
				.andExpect(status().isOk())
				// Ensure it appears we are authenticated with user
				.andExpect(authenticated().withUsername("user"));
	}

}
