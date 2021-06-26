package empl.employee.service;

import empl.employee.dto.RaportDto;
import empl.employee.entity.Project;
import empl.employee.exception.InvalidDataException;


/**
 * Service to make actions with different raports
 */
public interface RaportService {

    RaportDto getRaport(Long id) throws InvalidDataException;

    String formatRaport(Long projectId) throws InvalidDataException;

    void sendRaport(Project project) throws InvalidDataException;
}
