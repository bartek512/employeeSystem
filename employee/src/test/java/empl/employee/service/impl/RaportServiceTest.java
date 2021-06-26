package empl.employee.service.impl;


import empl.employee.dto.RaportDto;
import empl.employee.entity.Employee;
import empl.employee.entity.Project;
import empl.employee.exception.InvalidDataException;
import empl.employee.exception.NullFieldException;
import empl.employee.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RaportServiceTest {

    @Autowired
    RaportServiceImpl raportService;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void getCompleteRaoprt() throws InvalidDataException {

        //given
        Project project = projectRepository.findById(48L).orElseThrow();
        int size = project.getProjectAssignments().size();
        Employee employee1 = project.getProjectAssignments().get(0).getEmployee();
        Employee employee2 = project.getProjectAssignments().get(1).getEmployee();
        Employee employee3 = project.getProjectAssignments().get(2).getEmployee();
        Employee employee4 = project.getProjectAssignments().get(3).getEmployee();
        Employee employee5 = project.getProjectAssignments().get(4).getEmployee();
        Employee employee6 = project.getProjectAssignments().get(5).getEmployee();
        Employee employee7 = project.getProjectAssignments().get(6).getEmployee();

        //when
        RaportDto raport = raportService.getRaport(48L);
        List<Employee> expectedList = raport.getListOfEmpl();

        //then
        assertNotNull(expectedList);
        assertEquals(size, expectedList.size());
        assertEquals(employee1.getId(), expectedList.get(0).getId());
        assertEquals(employee2.getId(), expectedList.get(1).getId());
        assertEquals(employee3.getId(), expectedList.get(2).getId());
        assertEquals(employee4.getId(), expectedList.get(3).getId());
        assertEquals(employee5.getId(), expectedList.get(4).getId());
        assertEquals(employee6.getId(), expectedList.get(5).getId());
        assertEquals(employee7.getId(), expectedList.get(6).getId());
    }

    @Test
    void getRaportForNonExistingProjectShouldThrowInvalidDataException() throws InvalidDataException {

        assertThrows(InvalidDataException.class, () -> this.raportService.getRaport(55L));
    }

    @Test
    void getRaportForIdBelowZeroShouldThrowInvalidDataException() throws InvalidDataException {

        assertThrows(InvalidDataException.class, () -> this.raportService.getRaport(-5L));
    }

    @Test
    void checkFormatRaportMethod() throws InvalidDataException {

        //when
        String msg = raportService.formatRaport(48L);

        //then
        assertNotNull(msg);
        assertTrue(msg.contains("331 Karen Edwards, In project for  days 153 0.50"));
        assertTrue(msg.contains("29 Edward Stewart, In project for  days 153 0.50"));
        assertTrue(msg.contains("200 Steven Murphy, In project for  days 153 1.00"));
        assertTrue(msg.contains("276 David Edwards, In project for  days 153 1.00"));
        assertTrue(msg.contains("3 Konrad Wiśniewski, In project for  days 153 1.00"));
        assertTrue(msg.contains("208 Walter Edwards, In project for  days 153 1.00"));

    }

    @Test
    void checkFormatRaportMethodWithWrongIdShouldThrowInvalidDataExc() throws InvalidDataException {

        assertThrows(InvalidDataException.class, () -> this.raportService.formatRaport(55L));
    }

    @Test
    void checkFormatRaportMethodWithIdBelowZeroShouldThrowInvalidDataExc() throws InvalidDataException {

        assertThrows(InvalidDataException.class, () -> this.raportService.formatRaport(-5L));
    }

    @Test
    void checkFormatRaportMethodWithProjectWithoutManagerShouldThrowInvalidDataExc() throws InvalidDataException {

        //given
        Project project = projectRepository.findById(48L).orElseThrow();

        //when
        project.setManager(null);

        //when
        assertThrows(NullFieldException.class, () -> this.raportService.formatRaport(project.getId()));
    }
}
