package empl.employee.service;

import empl.employee.dto.ContractDto;
import empl.employee.entity.Department;
import empl.employee.entity.Employee;
import empl.employee.entity.Position;
import empl.employee.exception.BusinessException;

import java.math.BigDecimal;


/**
 * Service to make actions with contracts
 */
public interface ContractService {

    ContractDto addNewContract(Employee employee, Department department, Position position, BigDecimal salary) throws BusinessException;
}
