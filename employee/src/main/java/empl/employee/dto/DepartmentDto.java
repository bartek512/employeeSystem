package empl.employee.dto;

import empl.employee.entity.Employee;

public class DepartmentDto {

    private String name;

    private Employee manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }


    public static class DepartmentBuilder {

        private String name;

        private Employee manager;

        public DepartmentDto.DepartmentBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public DepartmentDto.DepartmentBuilder withManager(final Employee manager) {
            this.manager = manager;
            return this;
        }

        public DepartmentDto build() {
            final DepartmentDto result = new DepartmentDto();
            result.setName(name);
            result.setManager(this.manager);
            return result;
        }
    }
}
