package empl.employee.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class Position extends AbstractEntity {

	@NotNull
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CareerPath careerPath;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Grade grade;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CareerPath getCareerPath() {
		return this.careerPath;
	}

	public void setCareerPath(final CareerPath careerPath) {
		this.careerPath = careerPath;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(final Grade grade) {
		this.grade = grade;
	}

}
