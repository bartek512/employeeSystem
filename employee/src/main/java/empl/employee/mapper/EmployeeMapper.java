package empl.employee.mapper;

import empl.employee.dto.EmployeeDto;
import empl.employee.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper implements AbstractMapper<EmployeeDto, Employee> {

    @Override
    public EmployeeDto mapToDto(final Employee entity) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, EmployeeDto.class);
    }

    @Override
    public Employee mapToEntity(final EmployeeDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Employee.class);
    }

}
