package empl.employee.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Employee extends AbstractEntity {

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	@NotNull
	private String documentNumber;

	@Email
	@NotNull
	private String email;

	@Past
	@NotNull
	private LocalDate birthDate;

	@NotNull
	private LocalDate hireDate;

	private LocalDate terminationDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEE_ID")
	@OrderBy("START_DATE")
	private List<ProjectAssignment> projectAssignments;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEE_ID")
	@OrderBy("START_DATE")
	private List<Contract> contracts;

	public List<ProjectAssignment> getCurrentProjectAssignments() {
		return this.projectAssignments.stream()
				.filter(p -> p.getEndDate() == null)
				.collect(Collectors.toList());
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public Sex getSex() {
		return this.sex;
	}

	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(final DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return this.documentNumber;
	}

	public void setDocumentNumber(final String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(final LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public LocalDate getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(final LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}

	public List<ProjectAssignment> getProjectAssignments() {
		return this.projectAssignments;
	}

	public void setProjectAssignments(final List<ProjectAssignment> projectAssignments) {
		this.projectAssignments = projectAssignments;
	}

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(final List<Contract> contracts) {
		this.contracts = contracts;
	}

}
