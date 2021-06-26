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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for {@link ClientServiceImpl} using Spy for {@link ClientMapper}
 */
@ExtendWith(MockitoExtension.class)
public class ClientServiceImplWithSpyOnMapperTest {

    @Spy
    private final ClientMapper clientMapper = new ClientMapper();

    @Mock
    private ClientValidator clientValidator;

    @Mock
    private ClientRepository clientRepository;

    private ClientServiceImpl clientService;


    @BeforeEach
    public void setUp() {
        this.clientService = new ClientServiceImpl(this.clientRepository, this.clientMapper, this.clientValidator); // alternatywa do @InjectMocks
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
        when(this.clientValidator.validate(any(ClientDto.class))).thenThrow(new NullFieldException("name"));

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
        when(this.clientValidator.validate(any(ClientDto.class))).thenThrow(new NullFieldException("manager"));

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
        when(this.clientRepository.save(any(Client.class))).thenReturn(clientToBeSavedInRepository);
        when(this.clientValidator.validate(any(ClientDto.class))).thenReturn(Boolean.TRUE);
        // when
        final ClientDto addedClient = this.clientService.updateClient(clientToSave);
        // then
        assertNotNull(addedClient);
        assertEquals(clientToSave.getName(), addedClient.getName());
        assertEquals(clientToSave.getManager()
                .getId(), addedClient.getManager()
                .getId());
        verify(this.clientValidator, times(1)).validate(clientToSave);
        verify(this.clientMapper, times(1)).mapToEntity(any(ClientDto.class));
        verify(this.clientMapper, times(1)).mapToDto(any(Client.class));
        verify(this.clientRepository, times(1)).save(any(Client.class));
        verifyNoMoreInteractions(this.clientValidator);
        verifyNoMoreInteractions(this.clientMapper); // I can still use verify on Spy
        verifyNoMoreInteractions(this.clientRepository);
    }

    private Client mapToEntity(final ClientDto dto) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Client.class);
    }

}
