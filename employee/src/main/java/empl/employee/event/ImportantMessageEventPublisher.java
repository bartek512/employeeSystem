package empl.employee.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ImportantMessageEventPublisher {

    Logger log = Logger.getLogger(ImportantMessageEventPublisher.class.getName());

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishImportantMessage(ImportantMessageEvent importantMessageEvent) {
        String departmentName = importantMessageEvent.getDepartment().getName();
        String managerName = importantMessageEvent.getDepartment().getManager().getName();
        String managerSurname = importantMessageEvent.getDepartment().getManager().getSurname();
        String msg = "Prosze wszystkich o uzupelnianie raportow. Wyslane przez: " + managerName + " " + managerSurname + "z dzialu: " + " " + departmentName;
        importantMessageEvent.setMsg(msg);
        applicationEventPublisher.publishEvent(importantMessageEvent);
        log.info(msg);
    }
}
