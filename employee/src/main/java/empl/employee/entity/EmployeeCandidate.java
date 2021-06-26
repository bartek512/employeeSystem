package empl.employee.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class EmployeeCandidate extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false, updatable = false)
	private Department department;

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

	@NotNull
	private String email;

	@NotNull
	private LocalDate birthDate;

	private LocalDateTime interviewDate;

	@Enumerated(EnumType.STRING)
	private InterviewResult interviewResult;

	private String interviewDescription;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RecruitmentStatus recruitmentStatus;

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
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

	public LocalDateTime getInterviewDate() {
		return this.interviewDate;
	}

	public void setInterviewDate(final LocalDateTime interviewDate) {
		this.interviewDate = interviewDate;
	}

	public InterviewResult getInterviewResult() {
		return this.interviewResult;
	}

	public void setInterviewResult(final InterviewResult interviewResult) {
		this.interviewResult = interviewResult;
	}

	public String getInterviewDescription() {
		return this.interviewDescription;
	}

	public void setInterviewDescription(final String interviewDescription) {
		this.interviewDescription = interviewDescription;
	}

	public RecruitmentStatus getRecruitmentStatus() {
		return this.recruitmentStatus;
	}

	public void setRecruitmentStatus(final RecruitmentStatus recruitmentStatus) {
		this.recruitmentStatus = recruitmentStatus;
	}

}
