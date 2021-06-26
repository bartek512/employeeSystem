package empl.employee.exception;

public class InvalidFieldLengthException extends InvalidDataException {

	private static final long serialVersionUID = -8415461369988711012L;

	public InvalidFieldLengthException(final int maxLength) {
		super("Invalid field length, the max length is: " + maxLength);
	}

}
