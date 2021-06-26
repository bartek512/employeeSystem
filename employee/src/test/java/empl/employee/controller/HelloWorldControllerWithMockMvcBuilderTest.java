package empl.employee.controller;

import empl.employee.event.GreetEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link HelloWorldController}
 *
 * @see <a href="http://blog.marcnuri.com/mockmvc-spring-mvc-framework/">MockMvc – Spring MVC testing framework introduction: Testing Spring endpoints</a>
 * @see <a href="https://spring.io/guides/gs/testing-web/">Testing the Web Layer</a>
 * @see <a href="https://www.baeldung.com/spring-boot-testing">Testing in Spring Boot</a>
 */
@ExtendWith(MockitoExtension.class)
public class HelloWorldControllerWithMockMvcBuilderTest {

	private MockMvc mockMvc;

	@Mock
	ApplicationEventPublisher applicationEventPublisher;

	@BeforeEach
	public void setUp() {
		final HelloWorldController helloWorldController = new HelloWorldController(this.applicationEventPublisher);
		this.mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController)
				.build();
	}

	@Test
	public void hello_getForObject() throws Exception {
		// given
		final String url = "/greet";
		final String who = "John";
		final String expectedMessage = "Hello John!";
		// when
		this.mockMvc.perform(get(url).param("who", who))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("true")));
		// then
		verify(this.applicationEventPublisher).publishEvent(argThat(a -> {
			final GreetEvent greetEvent = (GreetEvent) a;
			return greetEvent.getMessage()
					.equals(expectedMessage);
		}));
		verifyNoMoreInteractions(this.applicationEventPublisher);
	}

}
