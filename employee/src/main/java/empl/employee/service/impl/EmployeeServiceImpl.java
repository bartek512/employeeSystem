package empl.employee.service.impl;

import empl.employee.entity.Employee;
import empl.employee.exception.BusinessException;
import empl.employee.repository.EmployeeRepository;
import empl.employee.service.EmployeeService;
import empl.employee.validation.EmployeeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LogManager.getLogger(ClientServiceImpl.class);

    private final EmployeeRepository employeeRepository;


    private final EmployeeValidator employeeValidator;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository, final EmployeeValidator employeeValidator) {
        this.employeeRepository = employeeRepository;
        this.employeeValidator = employeeValidator;
    }

    /**
     * Adds new Employee entity
     *
     * @param employee to add
     * @return saved emplyee
     * @throws BusinessException
     */
    @Override
    public Employee addNewEmployee(Employee employee) throws BusinessException {
        this.employeeValidator.validate(employee);
        final Employee savedEmpl = this.employeeRepository.save(employee);
        return savedEmpl;
    }
}
