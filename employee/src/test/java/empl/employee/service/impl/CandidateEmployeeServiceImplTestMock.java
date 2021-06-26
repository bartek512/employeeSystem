package empl.employee.service.impl;

import empl.employee.dto.CandidateEmployeeDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.*;
import empl.employee.mapper.CandidateEmployeeMapper;
import empl.employee.mapper.EmployeeMapper;
import com.capgemini.jstk.employee.entity.*;
import empl.employee.exception.BusinessException;
import empl.employee.repository.CandidateEmployeeRepository;
import empl.employee.repository.EmployeeRepository;
import empl.employee.validation.CandidateEmployeeValidator;
import empl.employee.validation.EmployeeValidator;
import hireEmpl.employee.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateEmployeeServiceImplTestMock {


    @Mock
    private CandidateEmployeeMapper candidateEmployeeMapper;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private CandidateEmployeeValidator candidateEmployeeValidator;

    @Mock
    private EmployeeValidator employeeValidator;

    @Mock
    private CandidateEmployeeRepository candidateEmployeeRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CandidateEmployeeServiceImpl candidateEmployeeService;


    @Test
    public void addNewEmployeeFromCandidate() throws Exception {

        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .build();

        //when
        EmployeeDto employeeDto = candidateEmployeeService.createEmployeeDtoFromCandidateDto(candidateEmployeeDto);

        //then
        assertNotNull(employeeDto);
        assertEquals(candidateEmployeeDto.getSurname(), employeeDto.getSurname());
        assertEquals(candidateEmployeeDto.getName(), employeeDto.getName());
        assertEquals(candidateEmployeeDto.getEmail(), employeeDto.getEmail());
        Assertions.assertEquals(candidateEmployeeDto.getSex(), employeeDto.getSex());
        assertEquals(candidateEmployeeDto.getBirthDate(), employeeDto.getBirthDate());
        assertEquals(candidateEmployeeDto.getDocumentNumber(), employeeDto.getDocumentNumber());
        Assertions.assertEquals(candidateEmployeeDto.getDocumentType(), employeeDto.getDocumentType());

    }

    @Test
    public void checkIfChangeRecruitmentStatus() throws Exception {

        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .build();

        EmployeeCandidate employeeCandidate = mapToEntity(candidateEmployeeDto);
        employeeCandidate.setRecruitmentStatus(RecruitmentStatus.NEW);
        CandidateEmployeeDto candidateEmployeeDto1 = mapToDto(employeeCandidate);

        when(this.candidateEmployeeValidator.validate(any(CandidateEmployeeDto.class))).thenReturn(Boolean.TRUE);
        when(this.candidateEmployeeMapper.mapToEntity(candidateEmployeeDto)).thenReturn(employeeCandidate);
        when(this.candidateEmployeeRepository.save(any(EmployeeCandidate.class))).thenReturn(employeeCandidate);
        when(this.candidateEmployeeMapper.mapToDto(employeeCandidate)).thenReturn(candidateEmployeeDto1);

        // when
        final CandidateEmployeeDto updatedEmployee = this.candidateEmployeeService.updateRecruitmentStatus(candidateEmployeeDto, RecruitmentStatus.NEW);

        //then
        assertNotNull(updatedEmployee);
        Assertions.assertEquals(RecruitmentStatus.NEW, updatedEmployee.getRecruitmentStatus());


    }

    @Test
    public void checkIfChangeRecruitmentStatusFromRejectedToNew() throws Exception {

        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withRecruitmentStatus(RecruitmentStatus.REJECTED)
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .build();

        EmployeeCandidate employeeCandidate = mapToEntity(candidateEmployeeDto);
        employeeCandidate.setRecruitmentStatus(RecruitmentStatus.NEW);
        CandidateEmployeeDto candidateEmployeeDto1 = mapToDto(employeeCandidate);

        when(this.candidateEmployeeValidator.validate(any(CandidateEmployeeDto.class))).thenReturn(Boolean.TRUE);
        when(this.candidateEmployeeMapper.mapToEntity(candidateEmployeeDto)).thenReturn(employeeCandidate);
        when(this.candidateEmployeeRepository.save(any(EmployeeCandidate.class))).thenReturn(employeeCandidate);
        when(this.candidateEmployeeMapper.mapToDto(employeeCandidate)).thenReturn(candidateEmployeeDto1);

        // when
        final CandidateEmployeeDto updatedEmployee = this.candidateEmployeeService.updateRecruitmentStatus(candidateEmployeeDto, RecruitmentStatus.NEW);

        //then
        assertNotNull(updatedEmployee);
        Assertions.assertEquals(RecruitmentStatus.NEW, updatedEmployee.getRecruitmentStatus());

    }

    @Test
    void hireEmployeeShouldReturnEmployeDtoWithTheSameData() throws BusinessException {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withRecruitmentStatus(RecruitmentStatus.REJECTED)
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .build();


        when(candidateEmployeeValidator.validate(candidateEmployeeDto)).thenReturn(Boolean.TRUE);
        EmployeeDto employeeDto = candidateEmployeeService.createEmployeeDtoFromCandidateDto(candidateEmployeeDto);
        Employee newEmployee = mapToEntityEmpl(employeeDto);
        when(employeeMapper.mapToEntity(any(EmployeeDto.class))).thenReturn(newEmployee);
        when(employeeValidator.validate(any(Employee.class))).thenReturn(Boolean.TRUE);
        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);
        when(employeeMapper.mapToDto(newEmployee)).thenReturn(mapToDtoEmpl(newEmployee));


        //when
        EmployeeDto finalEmplyeeDto = candidateEmployeeService.hireEmployee(candidateEmployeeDto);

        //then
        assertNotNull(finalEmplyeeDto);
        assertEquals(candidateEmployeeDto.getName(), finalEmplyeeDto.getName());
        assertEquals(candidateEmployeeDto.getSurname(), finalEmplyeeDto.getSurname());
        Assertions.assertEquals(candidateEmployeeDto.getSex(), finalEmplyeeDto.getSex());
        Assertions.assertEquals(candidateEmployeeDto.getDocumentType(), finalEmplyeeDto.getDocumentType());
        assertEquals(candidateEmployeeDto.getDocumentNumber(), finalEmplyeeDto.getDocumentNumber());
        assertEquals(candidateEmployeeDto.getEmail(), finalEmplyeeDto.getEmail());
        assertEquals(candidateEmployeeDto.getBirthDate(), finalEmplyeeDto.getBirthDate());
    }


    private EmployeeCandidate mapToEntity(CandidateEmployeeDto employeeDto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeDto, EmployeeCandidate.class);
    }

    private Employee mapToEntityEmpl(EmployeeDto employeeDto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeDto, Employee.class);
    }

    private CandidateEmployeeDto mapToDto(EmployeeCandidate employeeCandidate) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeCandidate, CandidateEmployeeDto.class);
    }

    private EmployeeDto mapToDtoEmpl(Employee employee) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
