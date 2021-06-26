package empl.employee.exception;

public class InvalidDataException extends BusinessException {

	private static final long serialVersionUID = 8165221122340526782L;

	public InvalidDataException(final String message) {
		super(message);
	}

}
