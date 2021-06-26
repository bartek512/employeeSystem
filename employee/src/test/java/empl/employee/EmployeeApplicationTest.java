package empl.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link EmployeeApplication}
 */
@SpringBootTest
class EmployeeApplicationTest {

	@Autowired
	private ApplicationContext context;

	@Test
	void loadContext() {
		assertNotNull(this.context);
	}

}
