package empl.employee.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractMapper<D, T> {

	public abstract D mapToDto(T t);

	public abstract T mapToEntity(D d);

	public default List<D> mapToDtos(final List<T> list) {
		return list.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
	}

	public default List<T> mapToEntities(final List<D> list) {
		return list.stream()
				.map(this::mapToEntity)
				.collect(Collectors.toList());
	}

}
