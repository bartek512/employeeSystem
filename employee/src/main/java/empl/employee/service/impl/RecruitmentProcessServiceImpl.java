package empl.employee.service.impl;

import empl.employee.dto.ContractDto;
import empl.employee.entity.Department;
import empl.employee.entity.Employee;
import empl.employee.entity.Position;
import empl.employee.exception.BusinessException;
import empl.employee.service.ContractService;
import empl.employee.service.EmailService;
import empl.employee.service.EmployeeService;
import empl.employee.service.RecruitmentProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class RecruitmentProcessServiceImpl implements RecruitmentProcessService {

    private final EmailService emailService;

    private final EmployeeService employeeService;

    @Autowired
    public RecruitmentProcessServiceImpl(EmailService emailService, EmployeeService employeeService) {
        this.emailService = emailService;
        this.employeeService = employeeService;
    }

    /**
     * This metod adds new employee, set to department and position and creates new contract
     *
     * @param employee   employee to add
     * @param department department of new emplyee
     * @param position   position of new empoyee
     * @param salary     salary of new employee
     * @return just hired employee
     * @throws BusinessException
     */
    @Override
    public Employee hireNewEmployee(Employee employee, Department department, Position position, BigDecimal salary) throws BusinessException {
        Employee hiredEmpl = employeeService.addNewEmployee(employee);
        emailService.sendEmailWithParams(employee.getEmail(), "Wynik rekrutacji", new Object[]{position.getName(), salary});
        return hiredEmpl;
    }
}
