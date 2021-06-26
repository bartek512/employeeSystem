package empl.employee.event;

import org.springframework.context.ApplicationEvent;

public class GreetEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5069480721799386157L;

    final String message;

    public GreetEvent(final Object source, final String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
