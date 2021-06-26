package empl.employee.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @InjectMocks
    EmailServiceImpl emailService;

    @Mock
    JavaMailSender javaMailSender;


    @Test
    public void sendMailTest() {

        //given
        String reciever = "tstmail796@gmail.com";
        String tittle = "Tittle";
        String text = "Example";

        //when
        emailService.sendEmail(reciever, tittle, text);


        //then
        Mockito.verify(this.javaMailSender, times(1)).send(any(SimpleMailMessage.class));

    }

}
