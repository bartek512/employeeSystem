package empl.employee.dto;

import empl.employee.entity.Department;
import empl.employee.entity.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal salary;

    private Department department;

    private EmployeeDto employee;

    private Position position;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public static ContractBuilder builder() {
        return new ContractBuilder();
    }

    public static class ContractBuilder {

        private LocalDate startDate;

        private LocalDate endDate;

        private BigDecimal salary;

        private Department department;

        private EmployeeDto employee;

        private Position position;


        public ContractDto.ContractBuilder withStartDate(final LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public ContractDto.ContractBuilder withEndDate(final LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public ContractDto.ContractBuilder withSalary(final BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public ContractDto.ContractBuilder withDepartment(final Department department) {
            this.department = department;
            return this;
        }

        public ContractDto.ContractBuilder withEmployee(final EmployeeDto employee) {
            this.employee = employee;
            return this;
        }

        public ContractDto.ContractBuilder withPosition(final Position position) {
            this.position = position;
            return this;
        }

        public ContractDto build() {
            final ContractDto result = new ContractDto();
            result.setStartDate(this.startDate);
            result.setEndDate(this.endDate);
            result.setSalary(this.salary);
            result.setDepartment(this.department);
            result.setEmployee(this.employee);
            result.setPosition(this.position);
            return result;
        }
    }
}
