package empl.employee;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;


/**
 * Example usage of hamcrest matchers
 *
 * @see <a href="https://www.baeldung.com/java-junit-hamcrest-guide">Testing with Hamcrest</a>
 * @see <a href="https://www.baeldung.com/hamcrest-collections-arrays">Hamcrest Collections Cookbook</a>
 */
public class HamcrestTest {

	@Test
	public void basicHamcrestMatchers() {
		// collections
		final List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		assertThat(scores, hasSize(4));
		assertThat(scores, contains(99, 100, 101, 105));
		assertThat(scores, containsInAnyOrder(105, 99, 101, 100));
		assertThat(scores, hasItems(100, 101));
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(200)));
		// String
		assertThat("", is(emptyString()));
		assertThat(null, is(emptyOrNullString()));
		// Array
		final Integer[] marks = {1, 2, 3};
		assertThat(marks, arrayWithSize(3));
		assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));
	}

}
