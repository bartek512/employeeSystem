package empl.employee.validation;

import empl.employee.exception.BusinessException;

public interface AbstractValidator<T> {

	/**
	 * @throws BusinessException
	 * 		if object is invalid
	 */
	public abstract boolean validate(T t) throws BusinessException;

	public default boolean isLengthCorrect(final String value, final int maxLength) {
		return value == null || value.length() <= maxLength;
	}

}
