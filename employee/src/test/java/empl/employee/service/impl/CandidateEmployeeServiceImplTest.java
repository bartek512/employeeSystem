package empl.employee.service.impl;

import empl.employee.dto.CandidateEmployeeDto;
import empl.employee.entity.DocumentType;
import empl.employee.entity.InterviewResult;
import empl.employee.entity.RecruitmentStatus;
import empl.employee.entity.Sex;
import empl.employee.mapper.CandidateEmployeeMapper;
import empl.employee.mapper.EmployeeMapper;
import empl.employee.exception.InvalidDataException;
import empl.employee.repository.CandidateEmployeeRepository;
import empl.employee.repository.EmployeeRepository;
import empl.employee.validation.CandidateEmployeeValidator;
import empl.employee.validation.EmployeeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CandidateEmployeeServiceImplTest {

    private CandidateEmployeeMapper candidateEmployeeMapper;

    private EmployeeMapper employeeMapper;

    private CandidateEmployeeValidator candidateEmployeeValidator;

    private EmployeeValidator employeeValidator;

    private CandidateEmployeeRepository candidateEmployeeRepository;

    private EmployeeRepository employeeRepository;

    private CandidateEmployeeServiceImpl candidateEmployeeService;

    @BeforeEach
    public void setUp() {
        this.candidateEmployeeMapper = new CandidateEmployeeMapper();
        this.employeeMapper = new EmployeeMapper();
        this.candidateEmployeeValidator = new CandidateEmployeeValidator();
        this.employeeValidator = new EmployeeValidator();
        this.candidateEmployeeRepository = Mockito.mock(CandidateEmployeeRepository.class);
        this.employeeRepository = Mockito.mock(EmployeeRepository.class);
        this.candidateEmployeeService = new CandidateEmployeeServiceImpl(this.candidateEmployeeValidator, this.employeeValidator, this.employeeMapper,
                this.employeeRepository, this.candidateEmployeeMapper, this.candidateEmployeeRepository);
    }

    @Test
    public void addNewEmployeeCandidateWithRecruitmentStatusRejectedExpectedNullFieldException() throws InvalidDataException {

        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.REJECTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void addNewEmployeeCandidateWithInterviewResultNegativeExpectedNullFieldException() throws InvalidDataException {

        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.NEGATIVE)
                .build();

        //then
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithNameNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName(null)
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithSurnameNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname(null)
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithSexNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(null)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithDocumentTypeNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(null)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithDocumentNumberNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber(null)
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithEmailNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail(null)
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWithBirthDateNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(null)
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWitRecruitmentStatusNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(null)
                .withInterviewResult(InterviewResult.POSITIVE)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }

    @Test
    public void createNewEmployeeFromEmployeeCandidateShouldThrowInvalidDataExceptionWitInterviewResultNull() {
        //given
        final CandidateEmployeeDto candidateEmployeeDto = CandidateEmployeeDto.builder()
                .withName("Andrzej")
                .withSurname("Kowalski")
                .withSex(Sex.MALE)
                .withDocumentType(DocumentType.ID)
                .withDocumentNumber("1234")
                .withEmail("andrzej@gmail.com")
                .withBirthDate(LocalDate.of(1990, 12, 12))
                .withRecruitmentStatus(RecruitmentStatus.ACCEPTED)
                .withInterviewResult(null)
                .build();

        //then
        assertThrows(InvalidDataException.class, () -> this.candidateEmployeeService.hireEmployee(candidateEmployeeDto));
    }
}
