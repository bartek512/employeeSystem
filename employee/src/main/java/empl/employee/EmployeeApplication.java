package empl.employee;

import empl.employee.service.ClientService;
import empl.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmployeeApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeApplication.class);

    public static void main(final String[] args) {


        EmployeeRepository employeeRepository;

        try {
            final ConfigurableApplicationContext context = SpringApplication.run(EmployeeApplication.class, args);
            // sample call
            context.getBean(ClientService.class)
                    .getAllClients();
        } finally {
            LOG.info("Finished");
        }
    }
}
