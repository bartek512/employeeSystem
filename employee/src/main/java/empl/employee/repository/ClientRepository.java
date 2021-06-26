package empl.employee.repository;

import empl.employee.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface ClientRepository extends JpaRepository<Client, Long> {

	default List<Client> findByName(final String name) {
		return this.findAll()
				.stream()
				.filter(e -> name.equalsIgnoreCase(e.getName()))
				.collect(Collectors.toList());
	}
}
