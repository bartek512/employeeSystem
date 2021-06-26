package empl.employee.validation;

import empl.employee.dto.ClientDto;
import empl.employee.exception.NullFieldException;
import org.springframework.stereotype.Component;

@Component
public class ClientValidator implements AbstractValidator<ClientDto> {

	@Override
	public boolean validate(final ClientDto dto) throws NullFieldException {
		if (dto.getName() == null) {
			throw new NullFieldException("name");
		}
		if (dto.getManager() == null) {
			throw new NullFieldException("manager");
		}
		return true;
	}

}
