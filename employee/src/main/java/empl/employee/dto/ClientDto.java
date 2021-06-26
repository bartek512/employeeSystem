package empl.employee.dto;

public class ClientDto {

	private Long id;

	private String name;

	private EmployeeDto manager;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public EmployeeDto getManager() {
		return this.manager;
	}

	public void setManager(final EmployeeDto manager) {
		this.manager = manager;
	}

	public static ClientBuilder builder() {
		return new ClientBuilder();
	}

	//Builder
	public static class ClientBuilder {

		private Long id;

		private String name;

		private EmployeeDto manager;

		public ClientBuilder withId(final Long id) {
			this.id = id;
			return this;
		}

		public ClientBuilder withName(final String name) {
			this.name = name;
			return this;
		}

		public ClientBuilder withManager(final EmployeeDto manager) {
			this.manager = manager;
			return this;
		}

		public ClientDto build() {
			final ClientDto result = new ClientDto();
			result.setId(this.id);
			result.setName(this.name);
			result.setManager(this.manager);
			return result;
		}

	}
}
