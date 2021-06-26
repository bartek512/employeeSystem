package empl.employee.service.impl;

import empl.employee.dto.RaportDto;
import empl.employee.entity.Employee;
import empl.employee.entity.Project;
import empl.employee.entity.ProjectAssignment;
import empl.employee.exception.InvalidDataException;
import empl.employee.exception.NullFieldException;
import empl.employee.mapper.EmployeeMapper;
import empl.employee.repository.ProjectRepository;
import empl.employee.service.RaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
public class RaportServiceImpl implements RaportService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmailServiceImpl emailService;

    Logger log = Logger.getLogger(RaportServiceImpl.class.getName());


    public RaportServiceImpl(ProjectRepository projectRepository, EmployeeMapper employeeMapper, EmailServiceImpl emailService) {
        this.projectRepository = projectRepository;
        this.employeeMapper = employeeMapper;
        this.emailService = emailService;
    }

    /**
     * Gets raport about employees in project
     *
     * @param id of project which raport is needed
     * @return raport about employees
     * @throws InvalidDataException
     */
    public RaportDto getRaport(Long id) throws InvalidDataException {

        RaportDto raportDto = new RaportDto();
        Project project = projectRepository.findById(id).orElseThrow(() -> new InvalidDataException("Project not found"));

        for (ProjectAssignment pa : project.getProjectAssignments()) {
            raportDto.addEmployee(pa.getEmployee());
        }
        return raportDto;
    }

    /**
     * Formats raport into message to show in terminal
     *
     * @param projectId
     * @return formatted, ready to show message as raport
     * @throws InvalidDataException
     */
    public String formatRaport(Long projectId) throws InvalidDataException {

        RaportDto raportDto = getRaport(projectId);
        StringBuilder msg = new StringBuilder("");

        if (projectRepository.findById(projectId).orElseThrow().getManager() == null) {
            throw new NullFieldException("Project is without manager");
        }

        for (Employee employee : raportDto.getListOfEmpl()) {

            LocalDate startDate = employee.getProjectAssignments()
                    .stream()
                    .filter(p -> p.getProject().getId() == projectId)
                    .collect(Collectors.toList()).get(0).getStartDate();

            LocalDate endDate = employee.getProjectAssignments()
                    .stream()
                    .filter(p -> p.getProject().getId() == projectId)
                    .collect(Collectors.toList()).get(0).getEndDate();

            msg.append(employee.getId())
                    .append(" ")
                    .append(employee.getName())
                    .append(" ")
                    .append(employee.getSurname())
                    .append(", In project for  ")
                    .append("days ")
                    .append(daysInProject(startDate, endDate))
                    .append(" ")
                    .append(employee.getProjectAssignments().stream().filter(p -> p.getProject().getId() == projectId).collect(Collectors.toList()).get(0).getPartTime())
                    .append("\r\n");
        }

        log.info(msg.toString());

        return msg.toString();
    }

    /**
     * This method send generated raport to manager of the project
     *
     * @param project for which the raport is generated
     * @throws InvalidDataException
     */
    public void sendRaport(Project project) throws InvalidDataException {
        String msg = formatRaport(project.getId());
        emailService.sendEmail(project.getManager().getEmail(), "Raport", msg.toString());
    }

    /**
     * Measures days betweend two dates
     *
     * @param startDate date, from which days are counted
     * @param endDate   date which points end of counting days
     * @return days betweend startDate and endDate
     */
    private long daysInProject(LocalDate startDate, LocalDate endDate) {
        if (endDate == null) {
            long period = DAYS.between(startDate, LocalDate.now());
            return period;
        } else {
            long period = DAYS.between(startDate, endDate);
            return period;
        }
    }
}
