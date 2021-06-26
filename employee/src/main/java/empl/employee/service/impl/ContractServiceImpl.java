package empl.employee.service.impl;

import empl.employee.dto.ContractDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.Contract;
import empl.employee.entity.Department;
import empl.employee.entity.Employee;
import empl.employee.entity.Position;
import empl.employee.exception.BusinessException;
import empl.employee.mapper.ContractMapper;
import empl.employee.mapper.EmployeeMapper;
import empl.employee.repository.ContractRepository;
import empl.employee.service.ContractService;
import empl.employee.validation.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@Transactional
public class ContractServiceImpl implements ContractService {


    private final ContractRepository contractRepository;

    private final ContractMapper contractMapper;

    private final EmployeeMapper employeeMapper;

    private final EmployeeValidator employeeValidator;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, ContractMapper contractMapper, EmployeeMapper employeeMapper, EmployeeValidator employeeValidator) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
        this.employeeMapper = employeeMapper;
        this.employeeValidator = employeeValidator;
    }

    /**
     * Creates new contract for new employee
     *
     * @param employee   new employee
     * @param department department for new employee
     * @param position   position for new employee
     * @param salary     salary of new employeel
     * @return created contract
     * @throws BusinessException
     */
    @Override
    public ContractDto addNewContract(Employee employee, Department department, Position position, BigDecimal salary) throws BusinessException {
        this.employeeValidator.validate(employee);
        EmployeeDto employeeDto = employeeMapper.mapToDto(employee);

        final ContractDto contractDto = buildContractDto(department, position, salary, employeeDto);

        final Contract contractToSave = this.contractMapper.mapToEntity(contractDto);
        final Contract savedContract = this.contractRepository.save(contractToSave);
        return this.contractMapper.mapToDto(savedContract);
    }

    /**
     * Creates DTO object for Contract entity
     *
     * @return ContracDto object
     */
    private ContractDto buildContractDto(Department department, Position position, BigDecimal salary, EmployeeDto employeeDto) {
        return ContractDto.builder()
                .withStartDate(LocalDate.now())
                .withEndDate(LocalDate.now().plusMonths(3))
                .withEmployee(employeeDto)
                .withDepartment(department)
                .withPosition(position)
                .withSalary(salary)
                .build(); // czy nie warto dolozyc do kandydata pozycje i stawke  ?
    }
}
