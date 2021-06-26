package empl.employee.mapper;

import empl.employee.dto.ContractDto;
import empl.employee.entity.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper implements AbstractMapper<ContractDto, Contract> {
    @Override
    public ContractDto mapToDto(Contract entity) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ContractDto.class);
    }

    @Override
    public Contract mapToEntity(ContractDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Contract.class);
    }
}
