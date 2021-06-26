package empl.employee.service;

import empl.employee.dto.ClientDto;
import empl.employee.exception.InvalidDataException;

import java.util.List;


public interface ClientService {

	ClientDto updateClient(ClientDto client) throws InvalidDataException;

	List<ClientDto> getAllClients();

}
