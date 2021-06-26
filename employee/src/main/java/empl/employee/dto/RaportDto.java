package empl.employee.dto;

import empl.employee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class RaportDto {

    List<Employee> listOfEmpl = new ArrayList<Employee>();


    public void addEmployee(Employee employee) {
        listOfEmpl.add(employee);
    }

    public List<Employee> getListOfEmpl() {
        return listOfEmpl;
    }
}
