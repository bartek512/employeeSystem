package empl.employee.repository;

import empl.employee.entity.DocumentType;
import empl.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    default List<Employee> findByName(final String name) {
        return this.findAll()
                .stream()
                .filter(e -> name.equalsIgnoreCase(e.getName()))
                .collect(Collectors.toList());
    }

    default boolean doesEmployeeExists(final String documentNumber, final DocumentType documentType) {
        return this.findAll()
                .stream()
                .anyMatch(e -> documentType == e.getDocumentType() && documentNumber.equalsIgnoreCase(e.getDocumentNumber()));
    }
}
