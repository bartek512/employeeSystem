package empl.employee.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Project extends AbstractEntity {

	@NotNull
	private String name;

	@NotNull
	private LocalDate startDate;

	private LocalDate endDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ProjectType projectType;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID", nullable = false, updatable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID", nullable = false, updatable = false)
	private Employee manager;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false, updatable = false)
	private Department department;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	@OrderBy("START_DATE")
	private List<ProjectAssignment> projectAssignments;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

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

	public ProjectType getProjectType() {
		return this.projectType;
	}

	public void setProjectType(final ProjectType projectType) {
		this.projectType = projectType;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public Employee getManager() {
		return this.manager;
	}

	public void setManager(final Employee manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
	}

	public List<ProjectAssignment> getProjectAssignments() {
		return this.projectAssignments;
	}

	public void setProjectAssignments(final List<ProjectAssignment> projectAssignments) {
		this.projectAssignments = projectAssignments;
	}

}
