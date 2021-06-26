package empl.employee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link HelloWorldController}
 *
 * @see <a href="https://www.baeldung.com/spring-boot-testing">Testing in Spring Boot</a>
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void hello_getForObject() {
        // given
        final String url = "/hello";
        // when
        final String response = this.restTemplate.getForObject(url, String.class);
        // then
        assertEquals("Hello!", response);
    }

    @Test
    public void hello_exchange() {
        // given
        final String url = "/hello";
        // when
        final ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        // then
        assertEquals("Hello!", response.getBody());
    }

    @Test
    public void hello_getForObject_withPort() {
        // given
        final String url = "http://localhost:" + this.port + "/hello";
        // when
        final String response = this.restTemplate.getForObject(url, String.class);
        // then
        assertEquals("Hello!", response);
    }

}
