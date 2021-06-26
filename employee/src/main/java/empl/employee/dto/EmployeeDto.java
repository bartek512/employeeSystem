package empl.employee.dto;

import empl.employee.entity.DocumentType;
import empl.employee.entity.Sex;

import java.time.LocalDate;

public class EmployeeDto {

    private Long id;

    private String name;

    private String surname;

    private Sex sex;

    private DocumentType documentType;

    private String documentNumber;

    private String email;

    private LocalDate birthDate;

    private LocalDate hireDate;

    private LocalDate terminationDate;

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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }


    //Builder
    public static class EmployeeBuilder {

        private Long id;

        private String name;

        private String surname;

        private Sex sex;

        private DocumentType documentType;

        private String documentNumber;

        private String email;

        private LocalDate birthDate;

        private LocalDate hireDate;

        private LocalDate terminationDate;


        public EmployeeBuilder withId(final Long id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder withSurname(final String surname) {
            this.surname = surname;
            return this;
        }

        public EmployeeBuilder withSex(final Sex sex) {
            this.sex = sex;
            return this;
        }

        public EmployeeBuilder withDocumentType(final DocumentType documentType) {
            this.documentType = documentType;
            return this;
        }

        public EmployeeBuilder withDocumentNumber(final String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public EmployeeBuilder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder withBirthDate(final LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public EmployeeBuilder withHireDate(final LocalDate hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public EmployeeBuilder withTerminationDate(final LocalDate terminationDate) {
            this.terminationDate = terminationDate;
            return this;
        }

        public EmployeeDto build() {
            final EmployeeDto result = new EmployeeDto();
            result.setId(this.id);
            result.setName(this.name);
            result.setSurname(this.surname);
            result.setSex(this.sex);
            result.setDocumentType(this.documentType);
            result.setDocumentNumber(this.documentNumber);
            result.setEmail(this.email);
            result.setBirthDate(this.birthDate);
            result.setHireDate(this.hireDate);
            result.setTerminationDate(this.terminationDate);
            return result;
        }
    }
}
