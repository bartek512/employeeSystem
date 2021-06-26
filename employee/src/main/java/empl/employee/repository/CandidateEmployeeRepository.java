package empl.employee.repository;

import empl.employee.entity.EmployeeCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;


public interface CandidateEmployeeRepository extends JpaRepository<EmployeeCandidate, Long> {

    default List<EmployeeCandidate> findByIdd(final Long id) {
        return this.findAll()
                .stream()
                .filter(e -> id.equals(e.getId()))
                .collect(Collectors.toList());
    }
}
