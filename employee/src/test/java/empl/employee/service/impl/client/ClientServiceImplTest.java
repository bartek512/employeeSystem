package empl.employee.service.impl.client;

import empl.employee.dto.ClientDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.entity.Client;
import empl.employee.mapper.ClientMapper;
import empl.employee.service.impl.ClientServiceImpl;
import empl.employee.exception.NullFieldException;
import empl.employee.repository.ClientRepository;
import empl.employee.validation.ClientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ClientServiceImpl} (no persistence layer, no SpringRunner)
 */
public class ClientServiceImplTest {

    private ClientMapper clientMapper;

    private ClientValidator clientValidator;

    private ClientRepository clientRepository;

    private ClientServiceImpl clientService;


    @BeforeEach
    public void setUp() {
        this.clientMapper = new ClientMapper();
        this.clientValidator = new ClientValidator();
        this.clientRepository = Mockito.mock(ClientRepository.class);
        this.clientService = new ClientServiceImpl(this.clientRepository, this.clientMapper, this.clientValidator);
    }

    @Test
    public void addNewClientWithNameNullExpectedNullFieldException() throws Exception {
        // given
        final EmployeeDto employeeDto = EmployeeDto.builder()
                .withId(3L)
                .build();
        final ClientDto clientToSave = ClientDto.builder()
                .withName(null)
                .withManager(employeeDto)
                .build();

        //then
        NullFieldException e = assertThrows(NullFieldException.class, () -> this.clientService.updateClient(clientToSave));


    }

    @Test
    public void addNewClientWithManagerNullExpectedNullFieldException() throws Exception {
        // given
        final ClientDto clientToSave = ClientDto.builder()
                .withName("Lenovo")
                .withManager(null)
                .build();

        //then
        NullFieldException e = assertThrows(NullFieldException.class, () -> this.clientService.updateClient(clientToSave));
    }

    @Test
    public void addNewClient() throws Exception {
        // given
        final EmployeeDto employeeDto = EmployeeDto.builder()
                .withId(3L)
                .build();
        final ClientDto clientToSave = ClientDto.builder()
                .withName("Lenovo")
                .withManager(employeeDto)
                .build();
        final long idOfReturnedObject = 1L;
        final Client clientToBeSavedInRepository = mapToEntity(clientToSave);
        clientToBeSavedInRepository.setId(idOfReturnedObject);
        when(this.clientRepository.save(any())).thenReturn(clientToBeSavedInRepository);
        // when
        final ClientDto addedClient = this.clientService.updateClient(clientToSave);
        // then
        assertNotNull(addedClient);
        assertNotNull(addedClient.getId());
        assertEquals(clientToSave.getName(), addedClient.getName());
        assertEquals(clientToSave.getManager()
                .getId(), addedClient.getManager()
                .getId());
    }

    private Client mapToEntity(final ClientDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Client.class);
    }

}
