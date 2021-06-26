package empl.employee.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Contract extends AbstractEntity {


	@NotNull
	private LocalDate startDate;

	private LocalDate endDate;

	@NotNull
	private BigDecimal salary;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false, updatable = false)
	private Department department;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID", nullable = false, updatable = false)
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITION_ID", nullable = false, updatable = false)
	private Position position;

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(final BigDecimal salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

}
