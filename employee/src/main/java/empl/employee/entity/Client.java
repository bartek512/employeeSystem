package empl.employee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Client extends AbstractEntity {

	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "MANAGER_ID", nullable = false, updatable = false)
	private Employee manager;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENT_ID")
	@OrderBy("START_DATE")
	private List<Project> projects;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Employee getManager() {
		return this.manager;
	}

	public void setManager(final Employee manager) {
		this.manager = manager;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

}
