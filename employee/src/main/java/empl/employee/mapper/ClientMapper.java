package empl.employee.mapper;

import empl.employee.dto.ClientDto;
import empl.employee.entity.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements AbstractMapper<ClientDto, Client> {

    @Override
    public ClientDto mapToDto(final Client entity) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ClientDto.class);
    }

    @Override
    public Client mapToEntity(final ClientDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Client.class);
    }
}
