package empl.employee.service;

import empl.employee.entity.Department;
import empl.employee.entity.Employee;
import empl.employee.entity.Position;
import empl.employee.exception.BusinessException;

import java.math.BigDecimal;


/**
 * Service to recruit new employees
 */
public interface RecruitmentProcessService {
    public Employee hireNewEmployee(Employee employee, Department department, Position position, BigDecimal salary) throws BusinessException;
}
