package empl.employee.entity;

import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ProjectAssignment extends AbstractEntity {

	@NotNull
	private LocalDate startDate;

	private LocalDate endDate;

	@NotNull
	@Max(1)
	@Min(0)
	private BigDecimal partTime;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", nullable = false, updatable = false)
	private Project project;

	@ManyToOne(cascade = CascadeType.ALL)
	@Nullable
	@JoinColumn(name = "EMPLOYEE_ID", nullable = false, updatable = false)
	private Employee employee;

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

	public BigDecimal getPartTime() {
		return this.partTime;
	}

	public void setPartTime(final BigDecimal partTime) {
		this.partTime = partTime;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

}
