package empl.employee.repository;

import empl.employee.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Positionreposiroty extends JpaRepository<Position, Long> {
}
