package empl.employee.dto;

import com.capgemini.jstk.employee.entity.*;
import empl.employee.entity.*;
import hireEmpl.employee.entity.*;

import java.time.LocalDate;

public class CandidateEmployeeDto {

    private Department department;

    private String name;

    private String surname;

    private Sex sex;

    private DocumentType documentType;

    private String documentNumber;

    private String email;

    private LocalDate birthDate;

    private LocalDate interviewDate;

    private InterviewResult interviewResult;

    private String interviewDescription;

    private RecruitmentStatus recruitmentStatus;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public InterviewResult getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(InterviewResult interviewResult) {
        this.interviewResult = interviewResult;
    }

    public String getInterviewDescription() {
        return interviewDescription;
    }

    public void setInterviewDescription(String interviewDescription) {
        this.interviewDescription = interviewDescription;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public static EmployeeCandidateBuilder builder() {
        return new EmployeeCandidateBuilder();
    }

    public static class EmployeeCandidateBuilder {

        private Department department;

        private String name;

        private String surname;

        private Sex sex;

        private DocumentType documentType;

        private String documentNumber;

        private String email;

        private LocalDate birthDate;

        private LocalDate interviewDate;

        private InterviewResult interviewResult;

        private String interviewDescription;

        private RecruitmentStatus recruitmentStatus;

        public CandidateEmployeeDto.EmployeeCandidateBuilder withDepartment(final Department department) {
            this.department = department;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withSurname(final String surname) {
            this.surname = surname;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withSex(final Sex sex) {
            this.sex = sex;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withDocumentType(final DocumentType documentType) {
            this.documentType = documentType;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withDocumentNumber(final String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withBirthDate(final LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withInterviewDate(final LocalDate interviewDate) {
            this.interviewDate = interviewDate;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withInterviewResult(final InterviewResult interviewResult) {
            this.interviewResult = interviewResult;
            return this;
        }

        public CandidateEmployeeDto.EmployeeCandidateBuilder withRecruitmentStatus(final RecruitmentStatus recruitmentStatus) {
            this.recruitmentStatus = recruitmentStatus;
            return this;
        }

        public CandidateEmployeeDto build() {
            final CandidateEmployeeDto result = new CandidateEmployeeDto();
            result.setName(this.name);
            result.setSurname(this.surname);
            result.setSex(this.sex);
            result.setDocumentType(this.documentType);
            result.setDocumentNumber(this.documentNumber);
            result.setEmail(this.email);
            result.setBirthDate(this.birthDate);
            result.setInterviewDate(this.interviewDate);
            result.setInterviewResult(this.interviewResult);
            result.setRecruitmentStatus(this.recruitmentStatus);
            return result;
        }
    }
}
