package empl.employee.service.impl;

import empl.employee.dto.CandidateEmployeeDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.Employee;
import empl.employee.entity.EmployeeCandidate;
import empl.employee.entity.RecruitmentStatus;
import empl.employee.exception.BusinessException;
import empl.employee.exception.InvalidDataException;
import empl.employee.mapper.CandidateEmployeeMapper;
import empl.employee.mapper.EmployeeMapper;
import empl.employee.repository.CandidateEmployeeRepository;
import empl.employee.repository.EmployeeRepository;
import empl.employee.service.CandidateEmployeeService;
import empl.employee.validation.CandidateEmployeeValidator;
import empl.employee.validation.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class CandidateEmployeeServiceImpl implements CandidateEmployeeService {


    private final CandidateEmployeeValidator candidateEmployeeValidator;

    private final EmployeeValidator employeeValidator;

    private final EmployeeMapper employeeMapper;

    private final CandidateEmployeeMapper candidateEmployeeMapper;

    private final EmployeeRepository employeeRepository;

    private final CandidateEmployeeRepository candidateEmployeeRepository;


    @Autowired
    public CandidateEmployeeServiceImpl(CandidateEmployeeValidator candidateEmployeeValidator, EmployeeValidator employeeValidator, EmployeeMapper employeeMapper, EmployeeRepository employeeRepository, CandidateEmployeeMapper candidateEmployeeMapper, CandidateEmployeeRepository candidateEmployeeRepository) {
        this.candidateEmployeeValidator = candidateEmployeeValidator;
        this.employeeValidator = employeeValidator;
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.candidateEmployeeMapper = candidateEmployeeMapper;
        this.candidateEmployeeRepository = candidateEmployeeRepository;
    }

    /**
     * Adds new employee from candidate employee, saves employee entity and updates recruitment status
     *
     * @param candidateEmployeeDto a new employee to hire
     * @return EmployeeDto object, created from candidate
     * @throws BusinessException
     */
    @Override
    public EmployeeDto hireEmployee(CandidateEmployeeDto candidateEmployeeDto) throws BusinessException {

        this.candidateEmployeeValidator.validate(candidateEmployeeDto);
        final EmployeeDto employeeDto = createEmployeeDtoFromCandidateDto(candidateEmployeeDto);
        final Employee employeeToSave = employeeMapper.mapToEntity(employeeDto);
        this.employeeValidator.validate(employeeToSave);
        final Employee savedEmployee = this.employeeRepository.save(employeeToSave);
        updateRecruitmentStatus(candidateEmployeeDto, RecruitmentStatus.HIRED);
        return this.employeeMapper.mapToDto(savedEmployee);
    }

    /**
     * Method to create employeDto from candidateDto
     *
     * @param candidateEmployeeDto to create employeeDto
     * @return
     */
    public EmployeeDto createEmployeeDtoFromCandidateDto(CandidateEmployeeDto candidateEmployeeDto) {
        return EmployeeDto.builder()
                .withName(candidateEmployeeDto.getName())
                .withSurname(candidateEmployeeDto.getSurname())
                .withSex(candidateEmployeeDto.getSex())
                .withDocumentType(candidateEmployeeDto.getDocumentType())
                .withDocumentNumber(candidateEmployeeDto.getDocumentNumber())
                .withEmail(candidateEmployeeDto.getEmail())
                .withBirthDate(candidateEmployeeDto.getBirthDate())
                .withHireDate(LocalDate.now())
                .withTerminationDate(LocalDate.now().plusMonths(3))
                .build();

    }

    /**
     * Updates recruitment status
     *
     * @param candidateEmployeeDto candidate which is needed to update
     * @param recruitmentStatus    new recruitment status
     * @return
     * @throws InvalidDataException
     */
    public CandidateEmployeeDto updateRecruitmentStatus(CandidateEmployeeDto candidateEmployeeDto, RecruitmentStatus recruitmentStatus) throws InvalidDataException {
        this.candidateEmployeeValidator.validate(candidateEmployeeDto);
        candidateEmployeeDto.setRecruitmentStatus(recruitmentStatus);
        final EmployeeCandidate emplToSave = candidateEmployeeMapper.mapToEntity(candidateEmployeeDto);
        final EmployeeCandidate savedEmpl = candidateEmployeeRepository.save(emplToSave);
        return this.candidateEmployeeMapper.mapToDto(savedEmpl);// czy ma cos zwracac ?
    }
}