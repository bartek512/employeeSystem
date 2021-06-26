package empl.employee.service.impl.client;

import empl.employee.dto.ClientDto;
import empl.employee.dto.EmployeeDto;
import empl.employee.service.impl.ClientServiceImpl;
import empl.employee.exception.NullFieldException;
import empl.employee.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link ClientServiceImpl}
 */
@SpringBootTest
public class ClientServiceImplIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * This is the object, which we are really testing
     */
    @Autowired
    private ClientServiceImpl clientService;


    @BeforeEach
    public void setUp() {
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

        // then
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
        // when
        final ClientDto addedClient = this.clientService.updateClient(clientToSave);
        // then
        assertNotNull(addedClient);
        assertNotNull(addedClient.getId());
        assertEquals(clientToSave.getName(), addedClient.getName());
        assertEquals(clientToSave.getManager()
                .getId(), addedClient.getManager()
                .getId());
        assertTrue(this.clientRepository.findById(addedClient.getId())
                .isPresent());
    }

}
