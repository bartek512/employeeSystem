package empl.employee.exception;

public class NullFieldException extends InvalidDataException {

    private static final long serialVersionUID = -1800909472647629210L;


    public NullFieldException(final String field) {
        super("The required field is null: " + field);
    }

}
