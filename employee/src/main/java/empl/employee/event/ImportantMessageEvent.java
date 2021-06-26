package empl.employee.event;

import empl.employee.entity.Department;
import org.springframework.context.ApplicationEvent;

public class ImportantMessageEvent extends ApplicationEvent {

    private Department department;

    private String msg;


    public ImportantMessageEvent(Object source, Department department) {
        super(source);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
