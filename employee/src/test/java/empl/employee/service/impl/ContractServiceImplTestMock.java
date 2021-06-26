package empl.employee.service.impl;


import empl.employee.dto.ContractDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.Contract;
import empl.employee.entity.DocumentType;
import empl.employee.entity.Employee;
import empl.employee.entity.Sex;
import empl.employee.mapper.ContractMapper;
import empl.employee.mapper.EmployeeMapper;
import empl.employee.exception.BusinessException;
import empl.employee.repository.ContractRepository;
import empl.employee.validation.EmployeeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceImplTestMock {


    @Mock
    private ContractMapper contractMapper;

    @Mock
    private EmployeeValidator employeeValidator;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractServiceImpl contractService;


    @Test
    public void checkIfAddNewContract() throws BusinessException {

        //given
        final EmployeeDto employeeDto = EmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .build();

        final ContractDto contractDto = ContractDto.builder()
                .withStartDate(LocalDate.now())
                .withSalary(BigDecimal.valueOf(7000))
                .withEmployee(employeeDto)
                .build();

        final Employee employee = mapToEntity(employeeDto);
        final Contract contract = mapToEntityContract(contractDto);

        when(this.employeeValidator.validate(any(Employee.class))).thenReturn(Boolean.TRUE);
        when(this.employeeMapper.mapToDto(employee)).thenReturn(employeeDto);
        when(this.contractMapper.mapToEntity(any(ContractDto.class))).thenReturn(contract);
        when(this.contractRepository.save(any(Contract.class))).thenReturn(contract);
        when(this.contractMapper.mapToDto(contract)).thenReturn(contractDto);

        //when
        final ContractDto savedContractDto = contractService.addNewContract(employee, null, null, BigDecimal.valueOf(7000));

        //then
        assertNotNull(savedContractDto);
        Assertions.assertEquals(null, savedContractDto.getDepartment());
        Assertions.assertEquals(null, savedContractDto.getPosition());
        assertEquals(BigDecimal.valueOf(7000), savedContractDto.getSalary());
        assertEquals(employeeDto, savedContractDto.getEmployee());

    }


    public Contract mapToEntityContract(final ContractDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Contract.class);
    }

    public Employee mapToEntity(final EmployeeDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Employee.class);
    }
}
