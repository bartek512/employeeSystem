package empl.employee.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ImportantMessageEventListener implements ApplicationListener<ImportantMessageEvent> {

    Logger log = Logger.getLogger(ImportantMessageEventListener.class.getName());

    @Override
    public void onApplicationEvent(ImportantMessageEvent importantMessageEvent) {
        log.info("Wyslano wiadomosc: " + importantMessageEvent.getMsg());
    }
}
