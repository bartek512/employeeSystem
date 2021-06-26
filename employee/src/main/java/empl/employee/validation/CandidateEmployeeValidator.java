package empl.employee.validation;

import empl.employee.dto.CandidateEmployeeDto;
import empl.employee.entity.InterviewResult;
import empl.employee.entity.RecruitmentStatus;
import empl.employee.exception.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public class CandidateEmployeeValidator implements AbstractValidator<CandidateEmployeeDto> {

    @Override
    public boolean validate(CandidateEmployeeDto employeeDto) throws InvalidDataException {

        if (employeeDto.getInterviewResult() == InterviewResult.POSITIVE &&
                employeeDto.getRecruitmentStatus() == RecruitmentStatus.ACCEPTED) {
            return true;
        } else {
            throw new InvalidDataException("Employee must have positive interview result and accepted recruitment status");
        }
    }
}
