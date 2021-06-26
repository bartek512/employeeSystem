package empl.employee.service.impl;

import empl.employee.dto.DepartmentDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.*;
import com.capgemini.jstk.employee.entity.*;
import empl.employee.exception.BusinessException;
import empl.employee.repository.DepartmentRepository;
import empl.employee.repository.EmployeeRepository;
import empl.employee.repository.Positionreposiroty;
import hireEmpl.employee.entity.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
public class RecruitmentProcesServiceImplIntegrationTest {

    @Autowired
    RecruitmentProcessServiceImpl recruitmentProcessService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    Positionreposiroty positionreposirory;

    @MockBean
    JavaMailSender javaMailSender;

    @Test
    void hireNewEmployeeTest() throws BusinessException {
        //given
        final EmployeeDto employeeDto = EmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(null)
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withHireDate(LocalDate.now())
                .build();
        final Employee employee = mapToEntity(employeeDto);
        final DepartmentDto departmentDto = DepartmentDto.builder()
                .withName("Testing")
                .withManager(employee)
                .build();
        final Department department = mapToEntityDep(departmentDto);

        final BigDecimal salary = BigDecimal.valueOf(7000);

        //when
        Employee savedEmpl = employeeRepository.save(employee);
        Department savedDepartment = departmentRepository.save(department);
        Position positionToSave = positionreposirory.findById(1L).orElseThrow();
        Employee hiredEmpl = recruitmentProcessService.hireNewEmployee(savedEmpl, savedDepartment, positionToSave, salary);
        Mockito.verify(this.javaMailSender, times(1)).send(any(SimpleMailMessage.class));

        //then
        assertNotNull(hiredEmpl);
        assertNotNull(hiredEmpl.getId());
        assertEquals(savedEmpl.getName(), hiredEmpl.getName());
        assertEquals(savedEmpl.getSurname(), hiredEmpl.getSurname());
        assertEquals(savedEmpl.getEmail(), hiredEmpl.getEmail());
        assertEquals(savedEmpl.getDocumentNumber(), hiredEmpl.getDocumentNumber());
        assertEquals(savedEmpl.getDocumentType(), hiredEmpl.getDocumentType());
        assertEquals(savedEmpl.getBirthDate(), hiredEmpl.getBirthDate());
    }


    public Employee mapToEntity(final EmployeeDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Employee.class);
    }

    public Department mapToEntityDep(final DepartmentDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Department.class);
    }
}
