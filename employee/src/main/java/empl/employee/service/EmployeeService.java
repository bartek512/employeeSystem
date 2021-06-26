package empl.employee.service;

import empl.employee.entity.Employee;
import empl.employee.exception.BusinessException;


/**
 * Service to make actions with employees
 */
public interface EmployeeService {

    Employee addNewEmployee(Employee employee) throws BusinessException;
}
