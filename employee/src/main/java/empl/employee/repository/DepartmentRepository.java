package empl.employee.repository;

import empl.employee.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    default List<Department> findByIdd(final Long id) {
        return this.findAll()
                .stream()
                .filter(e -> id.equals(e.getId()))
                .collect(Collectors.toList());
    }
}
