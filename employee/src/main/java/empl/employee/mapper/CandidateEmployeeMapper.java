package empl.employee.mapper;

import empl.employee.dto.CandidateEmployeeDto;
import empl.employee.entity.EmployeeCandidate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateEmployeeMapper implements AbstractMapper<CandidateEmployeeDto, EmployeeCandidate> {
    @Override
    public CandidateEmployeeDto mapToDto(EmployeeCandidate employeeCandidate) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeCandidate, CandidateEmployeeDto.class);
    }

    @Override
    public EmployeeCandidate mapToEntity(CandidateEmployeeDto employeeDto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeDto, EmployeeCandidate.class);
    }
}
