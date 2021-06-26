package empl.employee.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Department extends AbstractEntity {

	@NotNull
	private String name;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "MANAGER_ID", nullable = false, updatable = false)
	private Employee manager;

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

}
