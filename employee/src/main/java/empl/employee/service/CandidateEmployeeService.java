package empl.employee.service;

import empl.employee.dto.CandidateEmployeeDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.RecruitmentStatus;
import empl.employee.exception.BusinessException;
import empl.employee.exception.InvalidDataException;

/**
 * Service to make actions with candidates
 */
public interface CandidateEmployeeService {

    EmployeeDto hireEmployee(CandidateEmployeeDto candidateEmployeeDto) throws BusinessException;

    EmployeeDto createEmployeeDtoFromCandidateDto(CandidateEmployeeDto candidateEmployeeDto);

    CandidateEmployeeDto updateRecruitmentStatus(CandidateEmployeeDto candidateEmployeeDto, RecruitmentStatus recruitmentStatus) throws InvalidDataException;

}
