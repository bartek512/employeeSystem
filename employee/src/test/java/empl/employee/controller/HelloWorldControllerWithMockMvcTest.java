package empl.employee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link HelloWorldController}
 *
 * @see <a href="https://spring.io/guides/gs/testing-web/">Testing the Web Layer</a>
 * @see <a href="https://www.baeldung.com/spring-boot-testing">Testing in Spring Boot</a>
 */
@WebMvcTest(HelloWorldController.class)
class HelloWorldControllerWithMockMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void hello_getForObject() throws Exception {
		// given
		final String url = "/hello";
		// when
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello!")));
	}

}
