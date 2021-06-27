package empl.employee.service.impl;

import empl.employee.dto.ClientDto;
import empl.employee.entity.Client;
import empl.employee.exception.NullFieldException;
import empl.employee.mapper.ClientMapper;
import empl.employee.repository.ClientRepository;
import empl.employee.service.ClientService;
import empl.employee.validation.ClientValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger LOG = LogManager.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final ClientValidator clientValidator;

    @Autowired
    public ClientServiceImpl(final ClientRepository clientRepository, final ClientMapper clientMapper, final ClientValidator clientValidator) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.clientValidator = clientValidator;
    }

    @Override
    public ClientDto updateClient(final ClientDto dto) throws NullFieldException {
        this.clientValidator.validate(dto);
        final Client entityToSave = this.clientMapper.mapToEntity(dto);
        final Client savedClient = this.clientRepository.save(entityToSave);
        return this.clientMapper.mapToDto(savedClient);
    }

    @Override
    public List<ClientDto> getAllClients() {
        final List<Client> clients = this.clientRepository.findAll();
        LOG.info("Founded: {} client(s)", clients.size());
        return this.clientMapper.mapToDtos(clients);
    }

}
