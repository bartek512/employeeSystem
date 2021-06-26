package empl.employee.service.impl;

import empl.employee.entity.Department;
import empl.employee.event.ImportantMessageEvent;
import empl.employee.event.ImportantMessageEventPublisher;
import empl.employee.repository.DepartmentRepository;
import empl.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ImportantMessageEventPublisher applicationEventPublisher;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ImportantMessageEventPublisher applicationEventPublisher) {
        this.departmentRepository = departmentRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Send important message to all employees in project, using template
     *
     * @param department email adresses of recievers
     */
    @Override
    public void sendImportantMessage(Department department) {
        ImportantMessageEvent importantMessageEvent = new ImportantMessageEvent(this, department);
        applicationEventPublisher.publishImportantMessage(importantMessageEvent);
    }
}
