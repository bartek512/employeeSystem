package empl.employee.validation;

import empl.employee.entity.DocumentType;
import empl.employee.entity.Employee;
import empl.employee.entity.Sex;
import empl.employee.exception.BusinessException;
import empl.employee.exception.InvalidDataException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class EmployeeValidator implements AbstractValidator<Employee> {

    @Override
    public boolean validate(final Employee employee) throws BusinessException {
        if (!(employee.getName() instanceof String)) {
            throw new InvalidDataException("Name must be a String");
        } else if (!(employee.getSurname() instanceof String)) {
            throw new InvalidDataException("Surname must be a String");
        } else if (!(employee.getSex() instanceof Sex)) {
            throw new InvalidDataException("Sex must not be null");
        } else if (!(employee.getDocumentType() instanceof DocumentType)) {
            throw new InvalidDataException("DocumentType must not be null");
        } else if (!(employee.getDocumentNumber() instanceof String)) {
            throw new InvalidDataException("Document number must not be String");
        } else if (!(employee.getEmail() instanceof String)) {
            throw new InvalidDataException("Email must not be String");
        } else if (!(employee.getBirthDate() instanceof LocalDate)) {
            throw new InvalidDataException("Employee must have a right birth date");
        } else if (!(employee.getHireDate() instanceof LocalDate)) {
            throw new InvalidDataException("Employee must have a right hire date");
        } else {
            return true;
        }

    }


}
