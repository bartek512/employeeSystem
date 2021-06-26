package empl.employee.service.impl;

import empl.employee.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${mail.hireWelcomeTemplate}")
    private String mailTemplate;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Send simple mail after hiring employee, using template
     *
     * @param to     reciever
     * @param title  tittle of message
     * @param params values, used to format mail (salary and position)
     */
    @Override
    public void sendEmailWithParams(String to, String title, Object[] params) {

        SimpleMailMessage message = new SimpleMailMessage();
        String message1 = MessageFormat.format(mailTemplate, params);

        message.setTo(to);
        message.setSubject(title);
        message.setText(message1);

        javaMailSender.send(message);
    }

    /**
     * Sends simple email message
     *
     * @param to    reciever
     * @param title tittle of the message
     * @param msg   body of the message
     */
    @Override
    public void sendEmail(String to, String title, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(title);
        message.setText(msg);

        javaMailSender.send(message);
    }


}
