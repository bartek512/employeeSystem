package empl.employee.service;


/**
 * Service to send emails
 */
public interface EmailService {

    void sendEmailWithParams(String to, String title, Object[] params);

    void sendEmail(String to, String title, String msg);
}
